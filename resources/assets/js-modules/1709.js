__d(function(e,i,t,o){function r(e,i){if(!n.default.supportsHighResolutionVideo())return e.mp4_200k;switch(i){case"2g":case"3g":return e.video_xs;case"4g":return u?e.video_md:e.video_sm;case"wifi":return u?e.video_xl:e.video_lg;default:return e.video_xs}}Object.defineProperty(o,"__esModule",{value:!0}),o.selectPlatformVideoUri=r;var d=i(44),s=i(755),n=babelHelpers.interopRequireDefault(s),u=d.Dimensions.get("window").width>400},1709);