package day_10.home_work;

public class Main {


    public static void main(String[] args) {

//        List<File> list = Utils.TestFiles.getFilesInFolder("src/main/java/trash/resources_for_tests_02");


//        BigInteger bigInteger = BigInteger.valueOf(0);
//        int count = 0;
//
//        for (File file : list) {
//            try (Scanner scanner = new Scanner(file)) {
//                while (scanner.hasNext()) {
//                    int temp = scanner.nextInt();
//                    if (temp > 0) {
//                       bigInteger = bigInteger.add(BigInteger.valueOf(temp));
//                    }
//                    System.out.println(bigInteger.longValue());
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
        Summator.doJob();
    }
}
