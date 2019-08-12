package com.airbnb.epoxy;

class ControllerModelList extends ModelList {
    private static final ModelListObserver OBSERVER = new ModelListObserver() {
        public void onItemRangeInserted(int positionStart, int itemCount) {
            throw new IllegalStateException("Models cannot be changed once they are added to the controller");
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            throw new IllegalStateException("Models cannot be changed once they are added to the controller");
        }
    };

    ControllerModelList(int expectedModelCount) {
        super(expectedModelCount);
        pauseNotifications();
    }

    /* access modifiers changed from: 0000 */
    public void freeze() {
        setObserver(OBSERVER);
        resumeNotifications();
    }
}
