__d(function(e,n,o,i){function _(e,n,o,i){var _=arguments.length>4&&void 0!==arguments[4]?arguments[4]:{};p.default.logEvent({event_name:R,page_name:A,event_data:babelHelpers.extends({option_amenity_id:e,react_native_app:"ListingSelfCheckinSettings",listing_id:o,operation:n,modal:i},_)})}function t(e,n){_(e.amenity.id,D.SELECT_OPTION,n,M.ADD_NEW,{existing_option:e.option_available})}function a(e,n){_(e,D.ADD,n,M.EDIT_DETAILS)}function E(e,n){_(e,D.UPDATE,n,M.EDIT_DETAILS)}function l(e,n){_(e,D.REMOVE,n,M.REMOVAL_CONFIRM)}function d(e,n){_(e,D.REMOVE_CONFIRM,n,M.REMOVAL_CONFIRM)}function O(e,n){_(e,D.REMOVE_CANCEL,n,M.REMOVAL_CONFIRM)}Object.defineProperty(i,"__esModule",{value:!0}),i.MODAL_TYPES=i.OPERATIONS=void 0,i.logSelectOption=t,i.logTapAdd=a,i.logTapUpdate=E,i.logTapRemove=l,i.logConfirmRemove=d,i.logCancelRemove=O;var c=n(686),p=babelHelpers.interopRequireDefault(c),R="self_check_in",A="manage_listing_check_in",D=i.OPERATIONS={SELECT_OPTION:"clicked_option",REMOVE_CONFIRM:"removed_option",REMOVE_CANCEL:"clicked_cancel",REMOVE:"clicked_remove",ADD:"added_option",UPDATE:"updated_option"},M=i.MODAL_TYPES={ADD_NEW:"add_new_option_modal",EDIT_DETAILS:"edit_details_modal",REMOVAL_CONFIRM:"removal_confirm_modal"}},2394);