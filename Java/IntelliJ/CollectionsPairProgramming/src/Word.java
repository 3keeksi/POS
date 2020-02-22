public class Word implements Comparable{
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(Object o) {
        Word o2 = (Word) o;
        return word.substring(1).compareTo(o2.getWord());
    }
}
