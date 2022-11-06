public class Decrypt {
    public static double[][] musicDecrypter(double[][] musicEncrypted, int[] intervalKey) {
        double[][] musicSplit = new double[musicEncrypted.length][musicEncrypted[0].length];

        for (int i = 0; i < musicEncrypted.length; i++) {
            for (int j = 0; j < musicEncrypted[0].length; j++) {
                musicSplit[intervalKey[i]][j] = musicEncrypted[i][j];
            }
        }
        return musicSplit;

    }

    public static double[] musicReassembler(double[][] musicSplit, int numIntervals) {
        int musicLength = musicSplit.length * musicSplit[0].length;
        double[] music = new double[musicLength];
        int count = 0;
        for (int i = 0; i < numIntervals; i++) {
            for (int j = 0; j < (musicLength / numIntervals); j++) {
                music[count] = musicSplit[i][j];
                count++;
            }
        }
        return music;
    }
}
