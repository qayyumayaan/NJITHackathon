public class RandNum {

    static void printArray(int[][] array) {

        System.out.println();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

    }

    static void printArray(int[] array) {

        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();

    }

    static void printArray(double[][] array) {

        System.out.println();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

    }

    static void printArray(double[] array) {

        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();

    }

    static boolean arrayComparer(int[] input, int flag) {
        // function checks if any int flag matches any index in input array
        boolean win = false;
        for (int i = 0; i < input.length; i++) {
            if (flag == input[i]) {
                win = true;
                break;
            }
        }
        return win;
    }

    public static void intervalEncrypter(int numIntervals, int[] intervalKey, int[] intervals) {
        for (int i = 0; i < numIntervals; i++) {
            intervals[i] = i;
            intervalKey[i] = -1;
            boolean valid = false;
            while (valid == false) {
                int selectedInterval = (int) Math.round(Math.random() * (numIntervals - 1)); // generates 0 to n-1
                if (arrayComparer(intervalKey, selectedInterval) == false) { // test for uniqueness
                    intervalKey[i] = selectedInterval;
                    valid = true;
                    break;
                }
            }
        }
    }

    public static int[] intervalDecrypter(int[] encrypted) {
        int[] decrypted = new int[encrypted.length];
        for (int i = 0; i < encrypted.length; i++) {
            decrypted[encrypted[i]] = encrypted[i];
        }

        return decrypted;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int size = 20000000;
        double[] music = new double[size];

        for (int i = 0; i < size; i++) {
            music[i] = (double) ((10 * Math.random()));
        }

        // printArray(music);
        int numIntervals = music.length / 100;
        // int numIntervals = 100;
        double[][] musicSplit = Encrypt.musicSplitter(music, numIntervals);
        int[] intervalKey = new int[numIntervals];
        double[][] musicEncrypted = new double[musicSplit.length][musicSplit[0].length];
        Encrypt.musicEncrypter(musicEncrypted, musicSplit, numIntervals, intervalKey);
        // printArray(musicEncrypted);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime / 1000000000);
        startTime = System.nanoTime();

        double[][] decryptedMusic = Decrypt.musicDecrypter(musicEncrypted, intervalKey);

        double[] finalMusic = Decrypt.musicReassembler(decryptedMusic, numIntervals);

        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println(totalTime / 1000000000);

        // // test to see if encryption/decryption is lossless
        // double[] test = new double[music.length];
        // double sum = 0;
        // for (int i = 0; i < music.length; i++) {
        // test[i] = Math.abs(finalMusic[i] - music[i]) * Math.pow(10, 3);
        // sum += Math.abs(finalMusic[i] - music[i]) * Math.pow(10, 3);
        // }
        // printArray(test);
        // System.out.println(sum);

        // Y is interval #
        // X is samples

    }

}
