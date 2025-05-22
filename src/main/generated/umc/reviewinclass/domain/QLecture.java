package umc.reviewinclass.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLecture is a Querydsl query type for Lecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLecture extends EntityPathBase<Lecture> {

    private static final long serialVersionUID = 414131626L;

    public static final QLecture lecture = new QLecture("lecture");

    public final EnumPath<umc.reviewinclass.domain.enums.Category> category = createEnum("category", umc.reviewinclass.domain.enums.Category.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath instructorName = createString("instructorName");

    public final EnumPath<umc.reviewinclass.domain.enums.Level> level = createEnum("level", umc.reviewinclass.domain.enums.Level.class);

    public final StringPath name = createString("name");

    public final EnumPath<umc.reviewinclass.domain.enums.Platform> platform = createEnum("platform", umc.reviewinclass.domain.enums.Platform.class);

    public QLecture(String variable) {
        super(Lecture.class, forVariable(variable));
    }

    public QLecture(Path<? extends Lecture> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLecture(PathMetadata metadata) {
        super(Lecture.class, metadata);
    }

}

