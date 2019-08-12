package com.airbnb.lottie;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.graphics.Path.Op;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.List;

@TargetApi(19)
class MergePathsContent implements PathContent {
    private final Path firstPath = new Path();
    private final MergePaths mergePaths;
    private final String name;
    private final Path path = new Path();
    private final List<PathContent> pathContents = new ArrayList();
    private final Path remainderPath = new Path();

    MergePathsContent(MergePaths mergePaths2) {
        if (VERSION.SDK_INT < 19) {
            throw new IllegalStateException("Merge paths are not supported pre-KitKat.");
        }
        this.name = mergePaths2.getName();
        this.mergePaths = mergePaths2;
    }

    /* access modifiers changed from: 0000 */
    public void addContentIfNeeded(Content content) {
        if (content instanceof PathContent) {
            this.pathContents.add((PathContent) content);
        }
    }

    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
        for (int i = 0; i < this.pathContents.size(); i++) {
            ((PathContent) this.pathContents.get(i)).setContents(contentsBefore, contentsAfter);
        }
    }

    public Path getPath() {
        this.path.reset();
        switch (this.mergePaths.getMode()) {
            case Merge:
                addPaths();
                break;
            case Add:
                opFirstPathWithRest(Op.UNION);
                break;
            case Subtract:
                opFirstPathWithRest(Op.REVERSE_DIFFERENCE);
                break;
            case Intersect:
                opFirstPathWithRest(Op.INTERSECT);
                break;
            case ExcludeIntersections:
                opFirstPathWithRest(Op.XOR);
                break;
        }
        return this.path;
    }

    public String getName() {
        return this.name;
    }

    private void addPaths() {
        for (int i = 0; i < this.pathContents.size(); i++) {
            this.path.addPath(((PathContent) this.pathContents.get(i)).getPath());
        }
    }

    @TargetApi(19)
    private void opFirstPathWithRest(Op op) {
        this.remainderPath.reset();
        this.firstPath.reset();
        for (int i = this.pathContents.size() - 1; i >= 1; i--) {
            PathContent content = (PathContent) this.pathContents.get(i);
            if (content instanceof ContentGroup) {
                List<PathContent> pathList = ((ContentGroup) content).getPathList();
                for (int j = pathList.size() - 1; j >= 0; j--) {
                    Path path2 = ((PathContent) pathList.get(j)).getPath();
                    path2.transform(((ContentGroup) content).getTransformationMatrix());
                    this.remainderPath.addPath(path2);
                }
            } else {
                this.remainderPath.addPath(content.getPath());
            }
        }
        PathContent lastContent = (PathContent) this.pathContents.get(0);
        if (lastContent instanceof ContentGroup) {
            List<PathContent> pathList2 = ((ContentGroup) lastContent).getPathList();
            for (int j2 = 0; j2 < pathList2.size(); j2++) {
                Path path3 = ((PathContent) pathList2.get(j2)).getPath();
                path3.transform(((ContentGroup) lastContent).getTransformationMatrix());
                this.firstPath.addPath(path3);
            }
        } else {
            this.firstPath.set(lastContent.getPath());
        }
        this.path.op(this.firstPath, this.remainderPath, op);
    }
}
