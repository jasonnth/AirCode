__d(function(e,t,n,r){Object.defineProperty(r,"__esModule",{value:!0});var o=t(412),i=babelHelpers.interopRequireDefault(o),s=t(271),a=babelHelpers.interopRequireDefault(s),u=t(44),l=t(42),p=babelHelpers.interopRequireDefault(l),c=t(743),b=t(410),f=babelHelpers.interopRequireDefault(b),d=t(419),h=babelHelpers.interopRequireDefault(d),y=void 0;"android"===u.Platform.OS&&(y=p.default.component({viewName:"RCTToolbarPusherView",mockComponent:function(){return babelHelpers.jsx(u.View,{})}}));var m=(0,c.forbidExtraProps)({children:i.default.node}),P={navigationState:i.default.any,airbnbInstanceId:i.default.string},v=function(e){function t(n,r){babelHelpers.classCallCheck(this,t);var o=babelHelpers.possibleConstructorReturn(this,e.call(this,n,r));return o.onLayoutIOS=o.onLayoutIOS.bind(o),o.onButtonPress=o.onButtonPress.bind(o),o.setContainer=o.setContainer.bind(o),o}return babelHelpers.inherits(t,e),t.prototype.onLayoutIOS=function(){this.remeasure()},t.prototype.remeasure=function(){var e=this;this.container&&this.container.measure(function(t,n,r,o,i,s){var a=s;h.default.setFoldOffset(a,e.context.airbnbInstanceId)})},t.prototype.onButtonPress=function(e){var t=s.Children.toArray(this.props.children).filter(function(e){return e&&e.props&&e.props.icon}).map(function(e){return e.props.onPress}),n=t[e];n&&n()},t.prototype.setContainer=function(e){this.container=e},t.prototype.childrenButtonArray=function(){return s.Children.toArray(this.props.children).filter(function(e){return e&&e.props&&e.props.icon}).map(function(e){return e.props.icon})},t.prototype.render=function(){switch(u.Platform.OS){case"ios":return babelHelpers.jsx(f.default.Screen,{buttons:this.childrenButtonArray(),onButtonPress:this.onButtonPress},void 0,a.default.createElement(u.View,{collabsible:!1,onLayout:this.onLayoutIOS,ref:this.setContainer}));case"android":return babelHelpers.jsx(f.default.Screen,{buttons:this.childrenButtonArray(),onButtonPress:this.onButtonPress},void 0,babelHelpers.jsx(y,{}));default:return null}},t}(s.Component);r.default=v,v.propTypes=m,v.contextTypes=P},830);