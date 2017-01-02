LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_STATIC_JAVA_LIBRARIES := \
    android-support-v4 \
    android-support-v7-appcompat \
    android-support-design

LOCAL_RESOURCE_DIR := \
    frameworks/support/v7/appcompat/res \
    frameworks/support/design/res \
    $(LOCAL_PATH)/res

LOCAL_AAPT_INCLUDE_ALL_RESOURCES := true
LOCAL_AAPT_FLAGS := \
    --extra-packages android.support.v7.appcompat \
    --extra-packages android.support.design \
    --auto-add-overlay

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_MODULE_TAGS := optional
LOCAL_PROGUARD_FLAG_FILES := proguard.flags
LOCAL_CERTIFICATE := platform
LOCAL_PRIVILEGED_MODULE := true

LOCAL_USE_FRAMEWORK_SMARTISANOS := true
LOCAL_PACKAGE_NAME := Sidebar

include $(BUILD_PACKAGE)
