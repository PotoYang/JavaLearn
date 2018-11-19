/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/24 14:14
 * Modified:
 * Description:
 */
public enum Definition {
    HIGH_DEFINITION("高清", 1),
    STANDARD_DEFINITION("标清", 2);

    Definition(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    private String name;
    private Integer index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
