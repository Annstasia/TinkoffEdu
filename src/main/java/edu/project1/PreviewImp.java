package edu.project1;

public class PreviewImp implements Preview {
    @Override
    public String preview(char[] state) {
        return new String(state);
    }
}
