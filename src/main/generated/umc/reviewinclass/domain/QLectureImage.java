package umc.reviewinclass.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureImage is a Querydsl query type for LectureImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureImage extends EntityPathBase<LectureImage> {

    private static final long serialVersionUID = 1179618769L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureImage lectureImage = new QLectureImage("lectureImage");

    public final umc.reviewinclass.domain.common.QBaseEntity _super = new umc.reviewinclass.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image_url = createString("image_url");

    public final QLecture lecture;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLectureImage(String variable) {
        this(LectureImage.class, forVariable(variable), INITS);
    }

    public QLectureImage(Path<? extends LectureImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureImage(PathMetadata metadata, PathInits inits) {
        this(LectureImage.class, metadata, inits);
    }

    public QLectureImage(Class<? extends LectureImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new QLecture(forProperty("lecture")) : null;
    }

}

