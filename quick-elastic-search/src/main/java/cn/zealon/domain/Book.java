package cn.zealon.domain;

/**
 * @author: tangyl
 * @since: 2019/5/7
 * @version: 1.0
 */
public class Book {

    private String bookId;
    private String bookName;
    private String authorName;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
