package kg.projectr1.projectr1.domain.comments;

public final class CommentApi {
    public static final String PUBLIC_BASE = "/public/api/v1/comment";
    public static final String PROTECTED_BASE = "/protected/api/v1/comment";

    public static final String FIND_BY_PRODUCT_ID = PUBLIC_BASE + "/all/product/{id}";
    public static final String PAGINATED = PUBLIC_BASE + "/paginated";
    public static final String SEARCH = PUBLIC_BASE + "/search";

    public static final String SAVE = PROTECTED_BASE;
    public static final String UPDATE = PROTECTED_BASE;
    public static final String DELETE_BY_ID = PROTECTED_BASE + "/{id}";
}
