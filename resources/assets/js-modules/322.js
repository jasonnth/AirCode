__d(function(e,n,t,o){"use strict";var r=n(60),a=n(183),i=n(56),l=n(113),s=n(93),p=n(122),d=r.PropTypes,c="viewPager",g=function(e){function n(){var t,o,l;babelHelpers.classCallCheck(this,n);for(var p=arguments.length,d=Array(p),g=0;g<p;g++)d[g]=arguments[g];return t=o=babelHelpers.possibleConstructorReturn(this,e.call.apply(e,[this].concat(d))),o.getInnerViewNode=function(){return o.refs[c].getInnerViewNode()},o._childrenWithOverridenStyle=function(){return r.Children.map(o.props.children,function(e){if(!e)return null;var n=babelHelpers.extends({},e.props,{style:[e.props.style,{position:"absolute",left:0,top:0,right:0,bottom:0,width:void 0,height:void 0}],collapsable:!1});return e.type&&e.type.displayName&&"RCTView"!==e.type.displayName&&e.type.displayName,r.createElement(e.type,n)})},o._onPageScroll=function(e){o.props.onPageScroll&&o.props.onPageScroll(e),"on-drag"===o.props.keyboardDismissMode&&s()},o._onPageScrollStateChanged=function(e){o.props.onPageScrollStateChanged&&o.props.onPageScrollStateChanged(e.nativeEvent.pageScrollState)},o._onPageSelected=function(e){o.props.onPageSelected&&o.props.onPageSelected(e)},o.setPage=function(e){i.dispatchViewManagerCommand(a.findNodeHandle(o),i.AndroidViewPager.Commands.setPage,[e])},o.setPageWithoutAnimation=function(e){i.dispatchViewManagerCommand(a.findNodeHandle(o),i.AndroidViewPager.Commands.setPageWithoutAnimation,[e])},l=t,babelHelpers.possibleConstructorReturn(o,l)}return babelHelpers.inherits(n,e),n.prototype.componentDidMount=function(){null!=this.props.initialPage&&this.setPageWithoutAnimation(this.props.initialPage)},n.prototype.render=function(){return r.createElement(h,babelHelpers.extends({},this.props,{ref:c,style:this.props.style,onPageScroll:this._onPageScroll,onPageScrollStateChanged:this._onPageScrollStateChanged,onPageSelected:this._onPageSelected,children:this._childrenWithOverridenStyle()}))},n}(r.Component);g.propTypes=babelHelpers.extends({},l.propTypes,{initialPage:d.number,onPageScroll:d.func,onPageScrollStateChanged:d.func,onPageSelected:d.func,pageMargin:d.number,keyboardDismissMode:d.oneOf(["none","on-drag"]),scrollEnabled:d.bool});var h=p("AndroidViewPager",g);t.exports=g},322);