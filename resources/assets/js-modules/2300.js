__d(function(e,t,n,r){function o(){return{type:C.LOAD_ID_COUNTRIES,promise:(0,E.getIDCountries)().then(function(e){var t=(0,T.parseIDCountries)(e),n=c.default.user().country||a.default.country();return{idCountries:t,selectedIDCountry:t.find(function(e){return e.countryCodeAlpha2===n})}}),meta:{toast:s.LOAD_ID_COUNTRIES_FAILED_TOAST}}}function u(e){var t=e&&e.countryCodeAlpha2;return(0,A.logAction)(A.ACTIONS.CHANGED_ID_COUNTRY,{country_code:t}),(0,f.logSubmitCountrySelectionEvent)(t),{type:C.ID_COUNTRY_CHANGED,payload:{selectedIDCountry:e}}}function i(e,t,n){return(0,A.logAction)(A.ACTIONS.UNSUPPORTED_ID_FEEDBACK,{country_code:e,unsupported_id_type:t,unsupported_id_name:n}),(0,f.logSendIDNotListedFeedbackEvent)(t,e,n),function(e){return e((0,I.addToast)(s.UNSUPPORTED_ID_FEEDBACK_SENT_TOAST))}}function l(){p.default.present(b.AUTHENTICATED_WEBVIEW,{url:(0,O.getWebUrl)("/help/article/"+y.IDENTITY_HELP_ARTICLE_ID)})}Object.defineProperty(r,"__esModule",{value:!0}),r.loadIDCountries=o,r.idCountryChanged=u,r.sendUnsupportedIDTypeFeedback=i,r.presentHelpView=l;var d=t(377),a=babelHelpers.interopRequireDefault(d),_=t(410),p=babelHelpers.interopRequireDefault(_),D=t(376),c=babelHelpers.interopRequireDefault(D),I=t(684),s=t(2301),C=t(2295),E=t(2302),T=t(2303),A=t(2304),f=t(2305),y=t(2312),N=t(1482),b=babelHelpers.interopRequireWildcard(N),O=t(1483)},2300);