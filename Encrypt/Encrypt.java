public class Encrypt { 

    public static double[][] musicSplitter(double[] music, int numIntervals) {
        int samples = music.length;
        int numSamples = samples / numIntervals;
        double[][] musicSplit = new double[numIntervals][numSamples];
        int count = 0;
        for (int i = 0; i < numIntervals; i++) {
            for (int j = 0; j < numSamples; j++) {
                musicSplit[i][j] = music[count];
                count++;
            }
        }
        return musicSplit;
    }

    public static void musicEncrypter(double[][] musicEncrypted, double[][] musicSplit, int numIntervals,
            int[] intervalKey) { // generates intervalKey and musicEncrypted
        int[] intervals = new int[numIntervals];
        int samples = musicSplit[0].length;
        for (int i = 0; i < numIntervals; i++) { // Y dir
            intervals[i] = i;
            intervalKey[i] = -1;
            boolean valid = false;
            while (valid == false) {
                int selectedInterval = (int) Math.round(Math.random() * (numIntervals - 1)); // generates 0 to n-1
                if (arrayComparer(intervalKey, selectedInterval) == false) { // test for uniqueness
                    intervalKey[i] = selectedInterval;
                    for (int j = 0; j < samples; j++) { // X dir
                        musicEncrypted[i][j] = musicSplit[intervalKey[i]][j];
                    }
                    valid = true;
                    break;
                }
            }
        }
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

}
