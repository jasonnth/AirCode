__d(function(e,t,r,s){Object.defineProperty(s,"__esModule",{value:!0}),s.default=function(e){var t=e.stroke,r=+e.strokeWidth;u.default.isNil(e.strokeWidth)&&(r=null);var s=e.strokeDasharray;return"string"==typeof s&&(s=s.split(k).map(function(e){return+e})),s&&1===s.length&&s.push(s[0]),{stroke:(0,o.default)(t),strokeOpacity:(0,l.default)(e.strokeOpacity),strokeLinecap:f[e.strokeLinecap]||0,strokeLinejoin:p[e.strokeLinejoin]||0,strokeDasharray:s||null,strokeWidth:r,strokeDashoffset:s?+e.strokeDashoffset||0:null,strokeMiterlimit:e.strokeMiterlimit||4}};var i=t(937),o=babelHelpers.interopRequireDefault(i),a=t(939),l=babelHelpers.interopRequireDefault(a),n=t(932),u=babelHelpers.interopRequireDefault(n),k=/\s*,\s*/,f={butt:0,square:2,round:1},p={miter:0,bevel:2,round:1}},940);