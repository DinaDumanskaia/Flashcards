package flashcards;

import java.io.*;
import java.util.*;

public class Main {
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final List<String> LIST_OF_CONSOLE_LINES = new ArrayList<>();
    private static final Map<String, Integer> ERROR_ANSWERS = new HashMap<>();
    //private static Map<String, Map<String, String>> filesToDictionaryMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String userAction;
        Map<String, String> usersMap = new HashMap<>();
        List<String> listOfAllConsoleLines = new ArrayList<>();

        do {
            String menu = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
            System.out.println(menu);
            listOfAllConsoleLines.add(menu);
            userAction = scanner.nextLine();
            listOfAllConsoleLines.add(userAction);
            switch (userAction) {
                case "add":
                    addToMap(usersMap);
                    break;
                case "remove":
                    removeFromMap(usersMap);
                    break;
                case "import":
                    importToMap(usersMap);
                    break;
                case "export":
                    exportFromMap(usersMap);
                    break;
                case "ask":
                    askTermsFromMap(usersMap);
                    break;
                case "exit":
                    System.out.println("Bye, bye!");
                    return;
                case "log":
                    saveLog(listOfAllConsoleLines);
                    break;
                case "hardest card":
                    showHardestCard();
                    break;
                case "reset stats":
                    resetStatus();
                    break;
                default:
                    break;
            }
        } while (!userAction.equals("exit"));


//        List<String> listOfTerms = new ArrayList<>();
//        System.out.println("Input the number of cards:");
//        int cardsAmount;
//        try {
//            cardsAmount = Integer.parseInt(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            throw new Exception("Error! Input should be a number");
//        }
//
//        Map<String, String> usersCardsWithDefinitions = fillTheMap(scanner, cardsAmount, listOfTerms);
//        checkUserKnowledge(scanner, usersCardsWithDefinitions, listOfTerms);
//        scanner.close();
    }

    private static void resetStatus() {
        ERROR_ANSWERS.clear();
        System.out.println("Card statistics have been reset.");
    }

    private static void showHardestCard() {
        int maxNumber = findMaxNumberOfMentions(ERROR_ANSWERS);
        List<String> hardestCards = findHardestCardsByMaxNumberOfMentions(ERROR_ANSWERS, maxNumber);
        if(hardestCards.size() == 1) {
            System.out.println("The hardest card is \"" + hardestCards.get(0) + "\". You have " + maxNumber + " errors answering it");
        } else if (hardestCards.size() > 1) {
            System.out.print("The hardest cards are ");
            for (int i = 0; i < hardestCards.size(); i++) {
                System.out.print( "\"" + hardestCards.get(i) + "\"");
                if (i != hardestCards.size() - 1) {
                    System.out.print(", ");
                }
            }
        } else {
            System.out.println("There are no cards with errors.");
        }
    }

    private static List<String> findHardestCardsByMaxNumberOfMentions(Map<String, Integer> errorAnswers, int maxNumber) {
        List<String> hardestCards = new ArrayList<>();

        errorAnswers.forEach((key, value) -> {
            if (value == maxNumber) {
                hardestCards.add(key);
            }
        });
        return hardestCards;
    }

    private static int findMaxNumberOfMentions(Map<String, Integer> errorAnswers) {
        int maxNumberOfMentions = Integer.MIN_VALUE;
        List<Integer> valueList = new ArrayList<>(errorAnswers.values());
        for(int a : valueList) {
            if (a >= maxNumberOfMentions) {
                maxNumberOfMentions = a;
            }
        }
        return maxNumberOfMentions;
    }

    private static void saveLog(List<String> listOfAllConsoleLines) throws IOException {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = scanner1.nextLine();
        FileWriter writer = new FileWriter(fileName);
        for(String a : listOfAllConsoleLines) {
            writer.write(a);
        }
        System.out.println("The log have been saved.");
    }

//    public static void main(String[] args) {
//        Map<String, String> dinaLove = new HashMap<>();
//        dinaLove.put("Dem", "the best man");
//        dinaLove.put("mama", "the best woman");
//        dinaLove.put("Maia", "the best friend");
//
//        Scanner scanner = new Scanner(System.in);
//        removeFromMap(dinaLove, scanner);
//    }

    private static void askTermsFromMap(Map<String, String> usersMap) {
        String timeToAsk = "How many times to ask?";
        System.out.println(timeToAsk);
        LIST_OF_CONSOLE_LINES.add(timeToAsk);
        Scanner scanner1 = new Scanner(System.in);
        int cardsToAsk = 0;
        try {
            cardsToAsk = scanner1.nextInt();
            LIST_OF_CONSOLE_LINES.add(String.valueOf(cardsToAsk));
        } catch (NumberFormatException e) {
            String errore = "Your input is not a correct number";
            System.out.println(errore);
            LIST_OF_CONSOLE_LINES.add(errore);
        }
        checkUserKnowledge(usersMap, cardsToAsk);
    }

    private static void exportFromMap(Map<String, String> usersMap) {
        String fileName = "File name:";
        System.out.println(fileName);
        LIST_OF_CONSOLE_LINES.add(fileName);
        Scanner scanner1 = new Scanner(System.in);
        String userFileName = scanner1.nextLine();
        LIST_OF_CONSOLE_LINES.add(userFileName);
        FileWriter writer = null;
        int counter = 0;

        //filesToDictionaryMap.put(userFileName, usersMap);
        try {
            writer = new FileWriter(userFileName);
            FlashCardManipulator flashCardManipulator = new FlashCardManipulator(usersMap);
            FlashCard flashCard = flashCardManipulator.nextCard();
            while(flashCard != null) {
                String key = flashCard.getWord();
                String definition = flashCard.getDefinition();

                if (key != null) {
                    writer.write(key + "\n");
                    writer.write(definition + "\n");
                    counter++;
                }

                flashCard = flashCardManipulator.nextCard();
            }
            String res = counter + " cards have been saved.";
            System.out.println(res);
            LIST_OF_CONSOLE_LINES.add(res);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void importToMap(Map<String, String> usersMap) {
        String fileName = "File name:";
        System.out.println(fileName);
        LIST_OF_CONSOLE_LINES.add(fileName);
        Scanner scanner1 = new Scanner(System.in);
        String userFileName = scanner1.nextLine();
        LIST_OF_CONSOLE_LINES.add(userFileName);
        String res;

        //Map<String, String> retVal = filesToDictionaryMap.get(userFileName);

        FileReader fileReader;
        String word;
        String definition;
        int counter = 0;
        try {
            fileReader = new FileReader(userFileName);
            BufferedReader reader = new BufferedReader(fileReader);

            do {
               word = reader.readLine();
               definition = reader.readLine();
               usersMap.remove(word); // remove previous mapping for provided key if exists
               usersMap.put(word, definition);
               if (word != null) {
                   counter++;
               }
            } while (word != null);
            res = counter + " cards have been loaded.";
            System.out.println(res);
            LIST_OF_CONSOLE_LINES.add(res);
        } catch (FileNotFoundException e) {
            res = "File not found";
            System.out.println(res);
            LIST_OF_CONSOLE_LINES.add(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void removeFromMap(Map<String, String> usersMap) {
        String whichCard = "Which card?";
        System.out.println(whichCard);
        LIST_OF_CONSOLE_LINES.add(whichCard);
        Scanner scanner1 = new Scanner(System.in);
        String usersChoice = scanner1.nextLine();
        LIST_OF_CONSOLE_LINES.add(usersChoice);
        String res;
        if (usersMap.containsKey(usersChoice)) {
            usersMap.remove(usersChoice);
            res = "The card has been removed";
        } else {
            res = "Can't remove \"" + usersChoice + "\": there is no such card.";
        }
        System.out.println(res);
        LIST_OF_CONSOLE_LINES.add(res);
    }

    private static void addToMap(Map<String, String> usersMap) {
        String theCard = "The card:";
        System.out.println(theCard);
        LIST_OF_CONSOLE_LINES.add(theCard);
        Scanner scanner1 = new Scanner(System.in);
        String userCard = scanner1.nextLine();
        LIST_OF_CONSOLE_LINES.add(userCard);
        String res;
        if (!usersMap.containsKey(userCard)) {
            String definition = "The definition of the card:";
            System.out.println(definition);
            LIST_OF_CONSOLE_LINES.add(definition);
            String userDefinition = scanner1.nextLine();
            LIST_OF_CONSOLE_LINES.add(userDefinition);
            if (!usersMap.containsValue(userDefinition)) {
                usersMap.put(userCard, userDefinition);
                res = "The pair (\"" + userCard + "\":\"" + userDefinition + "\") has been added.";
            } else {
                res = "The definition \"" + userDefinition + "\" already exists.";
            }
        } else {
            res = "The card \"" + userCard + "\" already exists.";
        }
        System.out.println(res);
        LIST_OF_CONSOLE_LINES.add(res);
    }

    private static void checkUserKnowledge(Map<String, String> map, int cardsAmount) {
        //взять значение ключ
        //спросить у пользователя значение
        //сравнить, соответствует значение ключу:
        Scanner scanner1 = new Scanner(System.in);
        String userDefinition;
        FlashCardManipulator flashCardManipulator = new FlashCardManipulator(map);
//        FlashCard flashCard = flashCardManipulator.nextCard();

        for (int i = 0; i < cardsAmount; i++) {
            int cardNumber = RANDOM.nextInt(map.size());
            FlashCard card = flashCardManipulator.getCard(cardNumber);
            String termToCheck = card.getWord();
            if (termToCheck != null) {
                String printDefinition = "Print the definition of \"" + termToCheck + "\":";
                System.out.println(printDefinition);
                LIST_OF_CONSOLE_LINES.add(printDefinition);
                userDefinition = scanner1.nextLine();
                LIST_OF_CONSOLE_LINES.add(userDefinition);
                checkUserAnswer(termToCheck, userDefinition, map);
            }
        }
    }

    private static void checkUserAnswer(String termToCheck, String userDefinition, Map<String, String> termToDefinitionMap) {
        // -соответствует
        // -не соответсвует:
        //   -есть подобное значение в map
        String usersTerm = findTermByDefinition(userDefinition, termToDefinitionMap);
        String res;
        if (termToCheck.equals(usersTerm)) {
            res = "Correct!";
            System.out.println(res);
            LIST_OF_CONSOLE_LINES.add(res);
        } else {
            String definitionFromTermToChek = findDefinitionByTerm(termToCheck, termToDefinitionMap);
            if (usersTerm != null) {
                res = "Wrong. The right answer is \"" + definitionFromTermToChek + "\", but your definition is correct for \"" + findTermByDefinition(userDefinition, termToDefinitionMap) + "\".";
            } else {
                res = "Wrong. The right answer is \"" + definitionFromTermToChek + "\".";
            }
            System.out.println(res);
            LIST_OF_CONSOLE_LINES.add(res);
            if (ERROR_ANSWERS.containsKey(termToCheck)) {
                int numberOfAsks = ERROR_ANSWERS.get(termToCheck);
                ERROR_ANSWERS.replace(termToCheck, numberOfAsks, numberOfAsks + 1);
            } else {
                ERROR_ANSWERS.put(termToCheck, 1);
            }
        }
    }

    private static String findDefinitionByTerm(String termToCheck, Map<String, String> termToDefinitionMap) {
        for(Map.Entry<String, String> pair : termToDefinitionMap.entrySet()) {
            if (pair.getKey() != null && pair.getKey().equals(termToCheck)) {
                return pair.getValue();
            }
        }
        return null;
    }

    private static String findTermByDefinition(String userDefinition, Map<String, String> termToDefinitionMap) {
        for(Map.Entry<String, String> pair : termToDefinitionMap.entrySet()) {
            if (pair.getValue() != null && pair.getValue().equals(userDefinition)) {
                return pair.getKey();
            }
        }
        return null;
    }

    private void testMap(Map<String, String> termToDefinitionMap) {
        // wrap map into FlashCardManipulator class
        FlashCardManipulator flashCardManipulator = new FlashCardManipulator(termToDefinitionMap);
        // call it for next FlashCard (if no more exists - returns null)
        FlashCard flashCard = flashCardManipulator.nextCard();

        while(flashCard != null) {
            String key = flashCard.getWord();
            String value = flashCard.getDefinition();

            System.out.println("Word = " + key);
            System.out.println("Definition = " + value);

            flashCard = flashCardManipulator.nextCard();
        }
    }

    private static Map<String, String> fillTheMap(Scanner scanner, int cardsAmount, List<String> listOfTerms) {
        Map<String, String> map = new HashMap<>();

        String key;
        String meaning;
        for (int i = 0; i < cardsAmount; i ++) {

            System.out.println("Card #" + (i + 1) + ":");
            key = scanner.nextLine();
            while (map.containsKey(key)) {
                System.out.println("The term \"" + key + "\" already exists. Try again:");
                //System.out.println("Card #" + (i + 1) + ":");
                key = scanner.nextLine();
            }

            System.out.println("The definition for card #" + (i + 1) + ":");
            meaning = scanner.nextLine();
            while (map.containsValue(meaning)) {
                System.out.println("The definition \"" + meaning + "\" already exists. Try again:");
                //System.out.println("The definition for card #" + (i + 1) + ":");
                meaning = scanner.nextLine();
            }

            map.put(key, meaning);
            listOfTerms.add(key);
        }

        return map;
    }

    static class FlashCardManipulator {
        private final Map<String, String> map;
        //private final Map<String, Integer> intMap;
        private Iterator<Map.Entry<String, String>> iterator;
        private final List<Map.Entry<String, String>> entryList;
        //private final List<Map.Entry<String, Integer>> intEntryList;

        public FlashCardManipulator(Map<String, String> map) {
            this.map = map;
            this.entryList = new ArrayList<>(map.entrySet());
            this.iterator = map.entrySet().iterator();
        }

/*        public FlashCardManipulator(Map<String, Integer> intMap) {
            this.map = intMap;
            this.entryList = new ArrayList<>(intMap.entrySet());
            this.iterator = intMap.entrySet().iterator();
        }*/

        public FlashCard nextCard() {
            if (iterator.hasNext()) {
                Map.Entry<String, String> nextElement = iterator.next();
                return new FlashCard(nextElement.getKey(), nextElement.getValue());
            } else {
                return null;
            }
        }

        public void reset() {
            iterator = map.entrySet().iterator();
        }

        public FlashCard getCard(int cardNumber) {
             Map.Entry<String, String> cardEntry = entryList.get(cardNumber);
             return new FlashCard(cardEntry.getKey(), cardEntry.getValue());
        }
    }

    static class FlashCard {
        String key;
        String value;


        public FlashCard(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getWord() {
            return key;
        }

        public String getDefinition() {
            return value;
        }

    }

}
