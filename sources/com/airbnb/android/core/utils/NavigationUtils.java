package com.airbnb.android.core.utils;

import android.content.Context;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import com.airbnb.android.core.AirDialogFragmentFacade;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.enums.FragmentTransitionType;

public class NavigationUtils {
    public static void showFragment(FragmentManager fragmentManager, Context context, Fragment fragment, int fragmentContainerId, FragmentTransitionType type, boolean addToBackStack) {
        showFragment(fragmentManager, context, fragment, fragmentContainerId, type, addToBackStack, null);
    }

    public static void showFragment(FragmentManager fragmentManager, Context context, Fragment fragment, int fragmentContainerId, FragmentTransitionType type, boolean addToBackStack, String tag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (!isContainerEmpty(fragmentManager, fragmentContainerId)) {
            if (type.popEnter <= 0 || type.popExit <= 0) {
                ft.setCustomAnimations(type.enter, type.exit);
            } else {
                ft.setCustomAnimations(type.enter, type.exit, type.popEnter, type.popExit);
            }
            if (addToBackStack) {
                ft.addToBackStack(tag != null ? tag : fragment.getTag());
            }
        }
        if (shouldShowAsDialog(context, fragment)) {
            ((DialogFragment) fragment).show(ft, tag);
        } else {
            ft.replace(fragmentContainerId, fragment, tag).commit();
        }
    }

    public static void showModal(FragmentManager fragmentManager, Context context, Fragment fragment, int contentContainer, int modalContainer, boolean addToBackStack) {
        showModal(fragmentManager, context, fragment, contentContainer, modalContainer, addToBackStack, null);
    }

    public static void showModal(FragmentManager fragmentManager, Context context, Fragment fragment, int contentContainer, int modalContainer, boolean addToBackStack, String tag) {
        Fragment fragmentToRemove;
        FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
        FragmentTransaction ft = fragmentManager.beginTransaction().setCustomAnimations(type.enter, type.exit, type.popEnter, type.popExit);
        if (addToBackStack) {
            ft.addToBackStack(tag != null ? tag : fragment.getTag());
        }
        if (shouldShowAsDialog(context, fragment)) {
            ((DialogFragment) fragment).show(ft, tag);
            return;
        }
        Fragment modalFragment = fragmentManager.findFragmentById(modalContainer);
        if (modalFragment != null) {
            fragmentToRemove = modalFragment;
        } else {
            fragmentToRemove = fragmentManager.findFragmentById(contentContainer);
        }
        if (fragmentToRemove != null) {
            ft.remove(fragmentToRemove);
        }
        ft.add(modalContainer, fragment, tag).commit();
        fragmentManager.executePendingTransactions();
    }

    public static boolean tryPopBackStackToFragment(FragmentManager fragmentManager, String tag) {
        boolean fragmentExists;
        if (isInBackStack(fragmentManager, tag)) {
            fragmentManager.popBackStack(tag, 0);
        } else {
            if (fragmentManager.findFragmentByTag(tag) != null) {
                fragmentExists = true;
            } else {
                fragmentExists = false;
            }
            if (!fragmentExists) {
                return false;
            }
            for (int i = fragmentManager.getBackStackEntryCount(); i > 0; i--) {
                fragmentManager.popBackStack();
            }
        }
        return true;
    }

    public static void popBackStackToFragment(FragmentManager fragmentManager, String tag) {
        if (!tryPopBackStackToFragment(fragmentManager, tag)) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Attempting to pop but tag does not exist and not first fragment"));
        }
    }

    private static boolean isInBackStack(FragmentManager fragmentManager, String tag) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            if (tag.equals(fragmentManager.getBackStackEntryAt(i).getName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean shouldShowAsDialog(Context context, Fragment fragment) {
        if (fragment instanceof AirDialogFragmentFacade) {
            return ((AirDialogFragmentFacade) fragment).shouldShowAsDialog(context);
        }
        return false;
    }

    private static boolean isContainerEmpty(FragmentManager fragmentManager, int fragmentContainerId) {
        return fragmentManager.findFragmentById(fragmentContainerId) == null;
    }
}
