package hanshunping.chapter04.singlelinkedlist;

/**
 * 节点
 */
class HeroNode {
    /**
     * 英雄排名
     */
    private Long no;
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
    private HeroNode next;

    HeroNode(Long no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
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

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
