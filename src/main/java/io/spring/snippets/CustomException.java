package io.spring.snippets;

public class CustomException {

    public static class FileWithoutExtensionException extends Exception{
        public FileWithoutExtensionException(String message) {
            super(message);
        }
    }

    public static class FileNotFoundException extends Exception{
        public FileNotFoundException(String message) {
            super(message);
        }
    }
}
