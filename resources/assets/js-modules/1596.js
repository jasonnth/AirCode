__d(function(e,t,a,d){Object.defineProperty(d,"__esModule",{value:!0});var i=t(412),u=babelHelpers.interopRequireDefault(i),f=t(1588);d.default=u.default.shape({dismissText:u.default.string,heroActions:u.default.arrayOf(u.default.shape({destinationType:u.default.oneOf([f.DESTINATION_TYPE_CALLBACK,f.DESTINATION_TYPE_LOCATION,f.DESTINATION_TYPE_URL,f.DESTINATION_TYPE_POST]),destination:u.default.oneOfType([u.default.func,u.default.string]),text:u.default.string,heroActionId:u.default.string.isRequired})),messages:u.default.arrayOf(u.default.string).isRequired,fullSize:u.default.bool}).isRequired},1596);