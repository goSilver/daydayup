package hanshunping.chapter04.joseph;

/**
 * @author csh
 * @date 2020/5/1 14:39
 */
public class Kid {
    /**
     * 编号
     */
    private Integer no;
    /**
     * 下一个小孩
     */
    private Kid next;

    public Kid(Integer no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Kid{" +
                "no=" + no +
                '}';
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Kid getNext() {
        return next;
    }

    public void setNext(Kid next) {
        this.next = next;
    }
}
