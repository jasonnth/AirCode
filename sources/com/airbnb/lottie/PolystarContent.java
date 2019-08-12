package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.PointF;
import java.util.List;

class PolystarContent implements AnimationListener, PathContent {
    private final BaseKeyframeAnimation<?, Float> innerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> innerRoundednessAnimation;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<?, Float> outerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> outerRoundednessAnimation;
    private final Path path = new Path();
    private final BaseKeyframeAnimation<?, Float> pointsAnimation;
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final BaseKeyframeAnimation<?, Float> rotationAnimation;
    private TrimPathContent trimPath;
    private final Type type;

    PolystarContent(LottieDrawable lottieDrawable2, BaseLayer layer, PolystarShape polystarShape) {
        this.lottieDrawable = lottieDrawable2;
        this.name = polystarShape.getName();
        this.type = polystarShape.getType();
        this.pointsAnimation = polystarShape.getPoints().createAnimation();
        this.positionAnimation = polystarShape.getPosition().createAnimation();
        this.rotationAnimation = polystarShape.getRotation().createAnimation();
        this.outerRadiusAnimation = polystarShape.getOuterRadius().createAnimation();
        this.outerRoundednessAnimation = polystarShape.getOuterRoundedness().createAnimation();
        if (this.type == Type.Star) {
            this.innerRadiusAnimation = polystarShape.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = polystarShape.getInnerRoundedness().createAnimation();
        } else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        layer.addAnimation(this.pointsAnimation);
        layer.addAnimation(this.positionAnimation);
        layer.addAnimation(this.rotationAnimation);
        layer.addAnimation(this.outerRadiusAnimation);
        layer.addAnimation(this.outerRoundednessAnimation);
        if (this.type == Type.Star) {
            layer.addAnimation(this.innerRadiusAnimation);
            layer.addAnimation(this.innerRoundednessAnimation);
        }
        this.pointsAnimation.addUpdateListener(this);
        this.positionAnimation.addUpdateListener(this);
        this.rotationAnimation.addUpdateListener(this);
        this.outerRadiusAnimation.addUpdateListener(this);
        this.outerRoundednessAnimation.addUpdateListener(this);
        if (this.type == Type.Star) {
            this.outerRadiusAnimation.addUpdateListener(this);
            this.outerRoundednessAnimation.addUpdateListener(this);
        }
    }

    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> contentsBefore, List<Content> list) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            Content content = (Content) contentsBefore.get(i);
            if ((content instanceof TrimPathContent) && ((TrimPathContent) content).getType() == Type.Simultaneously) {
                this.trimPath = (TrimPathContent) content;
                this.trimPath.addListener(this);
            }
        }
    }

    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        switch (this.type) {
            case Star:
                createStarPath();
                break;
            case Polygon:
                createPolygonPath();
                break;
        }
        this.path.close();
        Utils.applyTrimPathIfNeeded(this.path, this.trimPath);
        this.isPathValid = true;
        return this.path;
    }

    public String getName() {
        return this.name;
    }

    private void createStarPath() {
        double currentAngle;
        float x;
        float y;
        double currentAngle2;
        float radius;
        float cp1Roundedness;
        float cp2Roundedness;
        float cp1Radius;
        float cp2Radius;
        float points = ((Float) this.pointsAnimation.getValue()).floatValue();
        if (this.rotationAnimation == null) {
            currentAngle = 0.0d;
        } else {
            currentAngle = (double) ((Float) this.rotationAnimation.getValue()).floatValue();
        }
        double currentAngle3 = Math.toRadians(currentAngle - 90.0d);
        float anglePerPoint = (float) (6.283185307179586d / ((double) points));
        float halfAnglePerPoint = anglePerPoint / 2.0f;
        float partialPointAmount = points - ((float) ((int) points));
        if (partialPointAmount != 0.0f) {
            currentAngle3 += (double) ((1.0f - partialPointAmount) * halfAnglePerPoint);
        }
        float outerRadius = ((Float) this.outerRadiusAnimation.getValue()).floatValue();
        float innerRadius = ((Float) this.innerRadiusAnimation.getValue()).floatValue();
        float innerRoundedness = 0.0f;
        if (this.innerRoundednessAnimation != null) {
            innerRoundedness = ((Float) this.innerRoundednessAnimation.getValue()).floatValue() / 100.0f;
        }
        float outerRoundedness = 0.0f;
        if (this.outerRoundednessAnimation != null) {
            outerRoundedness = ((Float) this.outerRoundednessAnimation.getValue()).floatValue() / 100.0f;
        }
        float partialPointRadius = 0.0f;
        if (partialPointAmount != 0.0f) {
            partialPointRadius = innerRadius + ((outerRadius - innerRadius) * partialPointAmount);
            x = (float) (((double) partialPointRadius) * Math.cos(currentAngle3));
            y = (float) (((double) partialPointRadius) * Math.sin(currentAngle3));
            this.path.moveTo(x, y);
            currentAngle2 = currentAngle3 + ((double) ((anglePerPoint * partialPointAmount) / 2.0f));
        } else {
            x = (float) (((double) outerRadius) * Math.cos(currentAngle3));
            y = (float) (((double) outerRadius) * Math.sin(currentAngle3));
            this.path.moveTo(x, y);
            currentAngle2 = currentAngle3 + ((double) halfAnglePerPoint);
        }
        boolean longSegment = false;
        double numPoints = Math.ceil((double) points) * 2.0d;
        for (int i = 0; ((double) i) < numPoints; i++) {
            if (longSegment) {
                radius = outerRadius;
            } else {
                radius = innerRadius;
            }
            float dTheta = halfAnglePerPoint;
            if (partialPointRadius != 0.0f && ((double) i) == numPoints - 2.0d) {
                dTheta = (anglePerPoint * partialPointAmount) / 2.0f;
            }
            if (partialPointRadius != 0.0f && ((double) i) == numPoints - 1.0d) {
                radius = partialPointRadius;
            }
            float previousX = x;
            float previousY = y;
            x = (float) (((double) radius) * Math.cos(currentAngle2));
            y = (float) (((double) radius) * Math.sin(currentAngle2));
            if (innerRoundedness == 0.0f && outerRoundedness == 0.0f) {
                this.path.lineTo(x, y);
            } else {
                float cp1Theta = (float) (Math.atan2((double) previousY, (double) previousX) - 1.5707963267948966d);
                float cp1Dx = (float) Math.cos((double) cp1Theta);
                float cp1Dy = (float) Math.sin((double) cp1Theta);
                float cp2Theta = (float) (Math.atan2((double) y, (double) x) - 1.5707963267948966d);
                float cp2Dx = (float) Math.cos((double) cp2Theta);
                float cp2Dy = (float) Math.sin((double) cp2Theta);
                if (longSegment) {
                    cp1Roundedness = innerRoundedness;
                } else {
                    cp1Roundedness = outerRoundedness;
                }
                if (longSegment) {
                    cp2Roundedness = outerRoundedness;
                } else {
                    cp2Roundedness = innerRoundedness;
                }
                if (longSegment) {
                    cp1Radius = innerRadius;
                } else {
                    cp1Radius = outerRadius;
                }
                if (longSegment) {
                    cp2Radius = outerRadius;
                } else {
                    cp2Radius = innerRadius;
                }
                float cp1x = cp1Radius * cp1Roundedness * 0.47829f * cp1Dx;
                float cp1y = cp1Radius * cp1Roundedness * 0.47829f * cp1Dy;
                float cp2x = cp2Radius * cp2Roundedness * 0.47829f * cp2Dx;
                float cp2y = cp2Radius * cp2Roundedness * 0.47829f * cp2Dy;
                if (partialPointAmount != 0.0f) {
                    if (i == 0) {
                        cp1x *= partialPointAmount;
                        cp1y *= partialPointAmount;
                    } else if (((double) i) == numPoints - 1.0d) {
                        cp2x *= partialPointAmount;
                        cp2y *= partialPointAmount;
                    }
                }
                this.path.cubicTo(previousX - cp1x, previousY - cp1y, x + cp2x, y + cp2y, x, y);
            }
            currentAngle2 += (double) dTheta;
            if (!longSegment) {
                longSegment = true;
            } else {
                longSegment = false;
            }
        }
        PointF position = (PointF) this.positionAnimation.getValue();
        this.path.offset(position.x, position.y);
        this.path.close();
    }

    private void createPolygonPath() {
        double currentAngle;
        int points = (int) Math.floor((double) ((Float) this.pointsAnimation.getValue()).floatValue());
        if (this.rotationAnimation == null) {
            currentAngle = 0.0d;
        } else {
            currentAngle = (double) ((Float) this.rotationAnimation.getValue()).floatValue();
        }
        double currentAngle2 = Math.toRadians(currentAngle - 90.0d);
        float anglePerPoint = (float) (6.283185307179586d / ((double) points));
        float roundedness = ((Float) this.outerRoundednessAnimation.getValue()).floatValue() / 100.0f;
        float radius = ((Float) this.outerRadiusAnimation.getValue()).floatValue();
        float x = (float) (((double) radius) * Math.cos(currentAngle2));
        float y = (float) (((double) radius) * Math.sin(currentAngle2));
        this.path.moveTo(x, y);
        double currentAngle3 = currentAngle2 + ((double) anglePerPoint);
        double numPoints = Math.ceil((double) points);
        for (int i = 0; ((double) i) < numPoints; i++) {
            float previousX = x;
            float previousY = y;
            x = (float) (((double) radius) * Math.cos(currentAngle3));
            y = (float) (((double) radius) * Math.sin(currentAngle3));
            if (roundedness != 0.0f) {
                float cp1Theta = (float) (Math.atan2((double) previousY, (double) previousX) - 1.5707963267948966d);
                float cp2Theta = (float) (Math.atan2((double) y, (double) x) - 1.5707963267948966d);
                this.path.cubicTo(previousX - (((radius * roundedness) * 0.25f) * ((float) Math.cos((double) cp1Theta))), previousY - (((radius * roundedness) * 0.25f) * ((float) Math.sin((double) cp1Theta))), x + (radius * roundedness * 0.25f * ((float) Math.cos((double) cp2Theta))), y + (radius * roundedness * 0.25f * ((float) Math.sin((double) cp2Theta))), x, y);
            } else {
                this.path.lineTo(x, y);
            }
            currentAngle3 += (double) anglePerPoint;
        }
        PointF position = (PointF) this.positionAnimation.getValue();
        this.path.offset(position.x, position.y);
        this.path.close();
    }
}
