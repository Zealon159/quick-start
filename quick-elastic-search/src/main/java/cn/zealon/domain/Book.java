package cn.zealon.domain;

/**
 * 图书信息
 * @author: tangyl
 * @since: 2019/5/7
 * @version: 1.0
 */
public class Book {

    /** 图书ID */
    private String bookId;
    /** 图书名称 */
    private String bookName;
    /** 作者名称 */
    private String authorName;
    /** 图书状态：1-连载  2-暂停  3-完结*/
    private int bookStatus;
    /** 图书字数 */
    private Integer wordCount;
    /** 图书价格 */
    private Double bookPrice;
    /** 分类id */
    private Integer categoryId;
    /** 简介 */
    private String introduction;

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

    public int getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(int bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
