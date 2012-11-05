public class Signature extends ParentSecure {
    private String originalSignature;

    public Signature(String file) {
        this.originalSignature = checkSignature(file);
    }

    private String checkSignature(String file) {
        return null;
    }

    @Override
    protected boolean checkFile(String file) {
        return checkSignature(file).equals(this.originalSignature);
    }
}
