__d(function(e,t,a,r){"use strict";function n(e,t){return new Promise(function(a,r){e(babelHelpers.extends({},s,t),function(e){if(null!=e.error)switch(e.error){case"canceled":a({status:c.CANCELED});break;case"denied":a({status:c.DENIED});break;default:r(e.error)}else a({status:c.SUCCESS,uri:e.uri,data:e.data})})})}var o=t(44),i=o.NativeModules,u=i.ImagePickerManager,s={title:"Select a Photo",cancelButtonTitle:"Cancel",takePhotoButtonTitle:"Take Photo\u2026",chooseFromLibraryButtonTitle:"Choose from Library\u2026",quality:1,allowsEditing:!1},c={SUCCESS:0,CANCELED:1,DENIED:2};a.exports=babelHelpers.extends({},u,{showImagePicker:function(e,t){return"function"==typeof e&&(t=e,e={}),u.showImagePicker(babelHelpers.extends({},s,e),t)},launchCamera:function(e){return n(u.launchCamera,e)},launchImageLibrary:function(e){return n(u.launchImageLibrary,e)},StatusCodes:c})},1369);