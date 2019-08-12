__d(function(e,t,r,n){function o(e){return e&&e.__esModule?e:{default:e}}function p(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function a(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function u(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(n,"__esModule",{value:!0});var s=t(1134),i=o(s),l=function(){function e(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}return function(t,r,n){return r&&e(t.prototype,r),n&&e(t,n),t}}(),y=t(271),c=o(y),f=function(e){function t(){return p(this,t),a(this,(t.__proto__||Object.getPrototypeOf(t)).apply(this,arguments))}return u(t,e),l(t,[{key:"renderWick",value:function(e){return c.default.createElement("line",e)}},{key:"renderCandle",value:function(e){return c.default.createElement("rect",e)}},{key:"getCandleProps",value:function(e){var t=e.width,r=e.candleHeight,n=e.x,o=e.y,p=e.data,a=e.events,u=e.role,s=(0,i.default)({stroke:"black"},e.style),l=e.padding.left||e.padding,y=s.width||.5*(t-2*l)/p.length,c=n-y/2,f=e.shapeRendering||"auto";return(0,i.default)({x:c,y:o,style:s,role:u,width:y,height:r,shapeRendering:f},a)}},{key:"getWickProps",value:function(e){var t=e.x,r=e.y1,n=e.y2,o=e.events,p=(0,i.default)({stroke:"black"},e.style),a=e.shapeRendering||"auto",u=e.role||"presentation";return(0,i.default)({x1:t,x2:t,y1:r,y2:n,style:p,role:u,shapeRendering:a},o)}},{key:"render",value:function(){var e=this.getCandleProps(this.props),t=this.getWickProps(this.props);return c.default.cloneElement(this.props.groupComponent,{},this.renderWick(t),this.renderCandle(e))}}]),t}(c.default.Component);f.propTypes={index:c.default.PropTypes.number,x:y.PropTypes.number,y1:y.PropTypes.number,y2:y.PropTypes.number,y:y.PropTypes.number,events:y.PropTypes.object,candleHeight:y.PropTypes.number,shapeRendering:y.PropTypes.string,scale:y.PropTypes.object,style:y.PropTypes.object,datum:y.PropTypes.object,width:y.PropTypes.number,padding:y.PropTypes.oneOfType([y.PropTypes.number,y.PropTypes.object]),data:y.PropTypes.array,groupComponent:y.PropTypes.element,role:y.PropTypes.string},n.default=f},1225);