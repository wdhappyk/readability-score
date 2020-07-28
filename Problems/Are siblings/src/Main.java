import java.io.File;

class Siblings {

    public static boolean areSiblings(File f1, File f2) {
        String dirOfF1 = getDirectoryPath(f1);
        String dirOfF2 = getDirectoryPath(f2);
        return dirOfF1.equals(dirOfF2);
    }

    private static String getDirectoryPath(File file) {
        String fileName = file.getName();
        StringBuilder sb = new StringBuilder(file.getAbsolutePath());
        int absPathLength = sb.length();
        sb.delete(absPathLength - fileName.length(), absPathLength);
        return sb.toString();
    }
}