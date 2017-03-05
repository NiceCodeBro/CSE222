/**
 * Created by MSD on 21.02.2017.
 */
public class Book {
    private String bookName;
    private String bookAuthorName;
    private String numberOfPage;
    private String bookStatus;
    /**
     * Book default constructor
     * It is do that assing all book variables to 'NA'
     */
    public Book() {
        this.bookName = "NA";
        this.bookAuthorName = "NA";
        this.numberOfPage = "NA";
        this.bookStatus = "NA";
    }
    /**
     * GetBookName
     * Return the bookName variable.
     * @return bookName;
     */
    public String getBookName() {
        return bookName;
    }
    /**
     * GetBookStatus
     * Return the bookStatus variable.
     * @return bookStatus;
     */
    public String getBookStatus() {
        return bookStatus;
    }
    /**
     * GetBookAuthorName
     * Return the bookAuthorName variable
     * @return bookAuthorName;
     */
    public String getBookAuthorName() {
        return bookAuthorName;
    }

    /**
     * setBookName
     * @param bookName
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    /**
     * setBookStatus
     * @param bookStatus
     */
    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    /**
     * setBookAuthorName
     * @param bookAuthorName
     */
    public void setBookAuthorName(String bookAuthorName) {
        this.bookAuthorName = bookAuthorName;
    }

    /**
     * getPageNumber
     * Return the numberOfPage variable
     * @return numberOfPage;
     */
    public String getPageNumber() {
        return numberOfPage;
    }

    /**
     * setPageNumber
     * @param numberOfPage
     */
    public void setPageNumber(String numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    /**
     * toString()
     * return book information
     * @return tempString;
     */
    public String toString() {
        String tempString =  "Book Name: " + this.getBookName() +  "      Book Author: " + this.getBookAuthorName() + "      Book Pages Number: " + this.getPageNumber() + "      Book Status: ";

        if(this.getBookStatus().equals("true"))
            tempString = tempString + "available.";
        else
            tempString = tempString + "not available.";

        return tempString;
    }


    /**
     * writeFileBack()
     * return string to write to file
     * @return string
     */
    public String writeFileBack() {
        return this.getBookName() +  "," + this.getBookAuthorName() + "," + this.getPageNumber() + "," + this.getBookStatus() ;
    }


}
