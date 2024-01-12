import java.util.*;

public class javaproject{

    public static void main(String[] args) {
        Map<String, List<String>> peopleLanguages = new HashMap<>();
        peopleLanguages.put("Person 1", Arrays.asList("Hindi", "English", "Gujarati"));
        peopleLanguages.put("Person 2", Arrays.asList("Hindi", "Odia"));
        peopleLanguages.put("Person 3", Arrays.asList("Tamil", "English"));
        peopleLanguages.put("Person 4", Arrays.asList("Odia", "Spanish"));
        peopleLanguages.put("Person 5", Arrays.asList("Gujarati", "Tamil"));
        peopleLanguages.put("Person 6", Arrays.asList("Tamil", "Odia"));
        peopleLanguages.put("Person 7", Arrays.asList("Telugu", "English"));
        peopleLanguages.put("Person 8", Arrays.asList("Telugu", "Hindi"));
        peopleLanguages.put("Person 9", Arrays.asList("Marathi", "Hindi"));
        peopleLanguages.put("Person 10", Arrays.asList("English", "Spanish"));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first person: ");
        String person1 = scanner.nextLine();
        System.out.print("Enter the second person: ");
        String person2 = scanner.nextLine();

        List<String> translators = findTranslators(person1, person2, peopleLanguages);

        if (!translators.isEmpty()) {
            System.out.println(person1 + " can communicate with " + person2 + " through the following translators:");
            for (String translator : translators) {
                System.out.println("- " + translator);
            }
        } else {
            System.out.println("No direct or indirect translation path found between " + person1 + " and " + person2 + ".");
        }
    }

    private static List<String> findTranslators(String sourcePerson, String targetPerson, Map<String, List<String>> peopleLanguages) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        findTranslatorsDFS(sourcePerson, targetPerson, peopleLanguages, visited, path);
        return path;
    }

    private static boolean findTranslatorsDFS(String currentPerson, String targetPerson, Map<String, List<String>> peopleLanguages, Set<String> visited, List<String> path) {
        if (currentPerson.equals(targetPerson)) {
            path.add(currentPerson);
            return true;
        }

        visited.add(currentPerson);

        for (Map.Entry<String, List<String>> entry : peopleLanguages.entrySet()) {
            String nextPerson = entry.getKey();
            List<String> languages = entry.getValue();

            if (!visited.contains(nextPerson) && !Collections.disjoint(peopleLanguages.get(currentPerson), languages)) {
                path.add(currentPerson);

                if (findTranslatorsDFS(nextPerson, targetPerson, peopleLanguages, visited, path)) {
                    return true;
                }

                path.remove(path.size() - 1);
            }
        }

        return false;
    }
}
