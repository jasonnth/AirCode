__d(function(e,t,r,n){function o(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function p(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(n,"__esModule",{value:!0});var u=function(){function e(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}return function(t,r,n){return r&&e(t.prototype,r),n&&e(t,n),t}}(),l=t(271),a=function(e){return e&&e.__esModule?e:{default:e}}(l),s=t(1139),c=function(e){function t(){return o(this,t),p(this,(t.__proto__||Object.getPrototypeOf(t)).apply(this,arguments))}return i(t,e),u(t,[{key:"renderClipPath",value:function(e,t){return a.default.createElement("defs",null,a.default.createElement("clipPath",{id:t},a.default.createElement("rect",e)))}},{key:"render",value:function(){var e=this.props,t=e.clipId,r=e.clipWidth,n=e.clipHeight,o=e.translateX,p=e.clipPadding,i=s.Helpers.getPadding(this.props),u=function(e){return i[e]-(p[e]||0)},l={x:u("left")+o,y:u("top"),width:r-u("left")-u("right"),height:n-u("top")-u("bottom")};return this.renderClipPath(l,t)}}]),t}(a.default.Component);c.propTypes={clipId:l.PropTypes.number,clipPadding:l.PropTypes.shape({top:l.PropTypes.number,bottom:l.PropTypes.number,left:l.PropTypes.number,right:l.PropTypes.number}),clipHeight:s.PropTypes.nonNegative,clipWidth:s.PropTypes.nonNegative,padding:l.PropTypes.oneOfType([l.PropTypes.number,l.PropTypes.shape({top:l.PropTypes.number,bottom:l.PropTypes.number,left:l.PropTypes.number,right:l.PropTypes.number})]),translateX:l.PropTypes.number},c.defaultProps={translateX:0,clipPadding:{top:5,bottom:5,left:0,right:0}},n.default=c},1226);