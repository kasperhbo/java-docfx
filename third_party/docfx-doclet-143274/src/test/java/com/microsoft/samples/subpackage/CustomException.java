package com.microsoft.samples.subpackage;

/**
 * The type Custom exception.
 */
public class CustomException extends Exception {

    /**
     * Instantiates a new Custom exception.
     *
     * @param message the message
     */
    public CustomException(String message) {
    super(message);
  }

    /**
     * We need to have such method that throw exception declared in the same class
     *
     * @throws CustomException with reason message
     */
    public void makeSomething() throws CustomException {
    throw new CustomException("It happened!");
  }

  private class PrivateException {
    private String message;
  }
}
