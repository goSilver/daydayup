package hanshunping.chapter04.doublelinkedlist;

/**
 * @author csh
 * @date 2020/5/1 11:12
 */
public class DoubleDirectionHeroNode {
    /**
     * 英雄排名
     */
    private Integer no;
    /**
     * 英雄姓名
     */
    private String name;
    /**
     * 英雄名号
     */
    private String nickName;
    /**
     * 下一个节点
     */
    private DoubleDirectionHeroNode nextNode;
    /**
     * 上一个节点
     */
    private DoubleDirectionHeroNode preNode;

    public DoubleDirectionHeroNode(Integer no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleDirectionHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public DoubleDirectionHeroNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(DoubleDirectionHeroNode nextNode) {
        this.nextNode = nextNode;
    }

    public DoubleDirectionHeroNode getPreNode() {
        return preNode;
    }

    public void setPreNode(DoubleDirectionHeroNode preNode) {
        this.preNode = preNode;
    }
}
