__d(function(e,r,s,o){Object.defineProperty(o,"__esModule",{value:!0}),o.touchablePropsKeys=o.touchableProps=o.responderPropsKeys=o.responderProps=o.pathProps=o.transformProps=o.strokeProps=o.clipProps=o.fillProps=o.numberProp=void 0;var p=r(271),n=r(44),t=p.PropTypes.oneOfType([p.PropTypes.string,p.PropTypes.number]),P={disabled:p.PropTypes.bool,onPress:p.PropTypes.func,onPressIn:p.PropTypes.func,onPressOut:p.PropTypes.func,onLongPress:p.PropTypes.func,delayPressIn:p.PropTypes.number,delayPressOut:p.PropTypes.number,delayLongPress:p.PropTypes.number},a=Object.keys(P),y=[].concat(babelHelpers.toConsumableArray(Object.keys(n.PanResponder.create({}).panHandlers)),["pointerEvents"]),l=y.reduce(function(e,r){return e[r]=p.PropTypes.func,e},{}),i={fill:p.PropTypes.string,fillOpacity:t,fillRule:p.PropTypes.oneOf(["evenodd","nonzero"])},u={clipRule:p.PropTypes.oneOf(["evenodd","nonzero"]),clipPath:p.PropTypes.string},c={name:p.PropTypes.string},T={stroke:p.PropTypes.string,strokeWidth:t,strokeOpacity:t,strokeDasharray:p.PropTypes.oneOfType([p.PropTypes.arrayOf(p.PropTypes.number),p.PropTypes.string]),strokeDashoffset:t,strokeLinecap:p.PropTypes.oneOf(["butt","square","round"]),strokeLinejoin:p.PropTypes.oneOf(["miter","bevel","round"]),strokeMiterlimit:t},f={scale:t,scaleX:t,scaleY:t,rotate:t,rotation:t,translate:t,translateX:t,translateY:t,x:t,y:t,origin:t,originX:t,originY:t,skew:t,skewX:t,skewY:t,transform:p.PropTypes.object},b=babelHelpers.extends({},i,T,u,f,l,P,c);o.numberProp=t,o.fillProps=i,o.clipProps=u,o.strokeProps=T,o.transformProps=f,o.pathProps=b,o.responderProps=l,o.responderPropsKeys=y,o.touchableProps=P,o.touchablePropsKeys=a},945);