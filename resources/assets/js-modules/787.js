__d(function(e,t,o,n){Object.defineProperty(n,"__esModule",{value:!0});var l=t(412),r=babelHelpers.interopRequireDefault(l),i=t(271),s=babelHelpers.interopRequireDefault(i),c=t(44),p=t(743),a=t(788),d=babelHelpers.interopRequireDefault(a),u=t(785),f=babelHelpers.interopRequireDefault(u),h=t(786),b=babelHelpers.interopRequireDefault(h),S=(0,p.forbidExtraProps)(babelHelpers.extends({},c.ScrollView.propTypes,{withInsets:b.default,onMomentumScrollEnd:r.default.func,ignoreScrollDeltaAndroid:r.default.bool,children:r.default.node})),m={withInsets:[],onMomentumScrollEnd:null,ignoreScrollDeltaAndroid:!0,scrollEventThrottle:200},y={airbnbInstanceId:r.default.string},v={makePointVisible:r.default.func},w=function(e){function t(o,n){babelHelpers.classCallCheck(this,t);var l=babelHelpers.possibleConstructorReturn(this,e.call(this,o,n));return l.scrollPosition=0,l.setScrollView=l.setScrollView.bind(l),l.handleScroll=l.handleScroll.bind(l),l}return babelHelpers.inherits(t,e),t.prototype.componentDidMount=function(){this.mounted=!0},t.prototype.componentWillUnmount=function(){this.mounted=!1},t.prototype.getChildContext=function(){var e=this;return{makePointVisible:function(t){var o=arguments.length>1&&void 0!==arguments[1]?arguments[1]:0,n=(0,c.findNodeHandle)(e.scrollView);n&&c.UIManager.measureInWindow(n,function(n,l,r,i){if(e.mounted){var s=0;t<l?s=t-l-o:t>l+i&&(s=t-(l+i)+o),Math.abs(s)>10&&e.scrollView.scrollTo({y:e.scrollPosition+s,animated:!0})}})}}},t.prototype.setScrollView=function(e){this.scrollView=e},t.prototype.scrollTo=function(e){this.scrollView.scrollTo(e)},t.prototype.getScrollResponder=function(){return this.scrollView.getScrollResponder()},t.prototype.insetProps=function(){var e=this.props,t=e.withInsets,o=e.contentContainerStyle,n=(0,f.default)(t,this.props.contentInset);return{contentContainerStyle:[o,{paddingBottom:n.bottom,paddingTop:n.top}]}},t.prototype.handleScroll=function(e){var t=this.props.onScroll;t&&t(e),this.scrollPosition=e.nativeEvent.contentOffset.y},t.prototype.render=function(){var e=this.context.airbnbInstanceId,t=this.props.ignoreScrollDeltaAndroid;return s.default.createElement(d.default,babelHelpers.extends({},this.props,this.insetProps(),{airbnbSceneInstanceId:e,ignoreScrollDeltaAndroid:t,ref:this.setScrollView,onScroll:this.handleScroll,scrollEventThrottle:this.props.scrollEventThrottle}),this.props.children)},t}(i.Component);n.default=w,w.defaultProps=m,w.propTypes=S,w.contextTypes=y,w.childContextTypes=v},787);