__d(function(e,a,t,l){Object.defineProperty(l,"__esModule",{value:!0});var r=a(412),i=babelHelpers.interopRequireDefault(r),s=a(271),p=babelHelpers.interopRequireDefault(s),d=a(44),b=a(743),u=a(754),n=babelHelpers.interopRequireDefault(u),c=a(746),o=(0,b.forbidExtraProps)(babelHelpers.extends({},u.touchablePropTypes,c.AccessibilityPropTypes,{activeOpacity:i.default.number}));l.default=(0,n.default)({displayName:"PlatformTouchableOpacity",renderDefaultTouchable:function(e){return p.default.createElement(d.TouchableOpacity,babelHelpers.extends({activeOpacity:e.activeOpacity,disabled:e.disabled,onPress:e.onPress,style:e.style},(0,c.mergeTraits)((0,c.a11y)(e),e.disabled&&"disabled")),e.children)},propTypes:o,warnUntestedRipple:!0,needsViewWrap:!0})},843);