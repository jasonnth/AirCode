__d(function(e,t,o,r){Object.defineProperty(r,"__esModule",{value:!0});var a=t(834),l=t(271),s=babelHelpers.interopRequireDefault(l),n=t(44),p=t(422),i=babelHelpers.interopRequireDefault(p),h=t(1663),u={children:a.Types.element.isRequired,layout:a.Types.oneOf([h.LAYOUT_HORIZONTAL,h.LAYOUT_VERTICAL]),numberOfPhotosInGrid:a.Types.number.isRequired,onPress:a.Types.func,tablet:a.Types.bool},b={layout:h.LAYOUT_VERTICAL,onPress:function(){},tablet:!1},g=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.children,o=e.layout,r=e.numberOfPhotosInGrid,a=e.onPress,l=e.tablet,p=o===h.LAYOUT_HORIZONTAL,i=[l&&(p?c.photoLargeTabletLandscape:c.photoLargeTabletPortrait),!l&&(1===r?c.photoLargeSingle:c.photoLarge)];return babelHelpers.jsx(n.View,{style:[p&&(r>2?c.photoLong:c.photo),i,r>1&&(p?c.spaceRight:c.spaceBottom)]},void 0,babelHelpers.jsx(n.TouchableHighlight,{onPress:a},void 0,s.default.cloneElement(t,{style:[i,t.props.style]})))},t}(s.default.Component);r.default=g,g.defaultProps=b,g.propTypes=u;var c=i.default.create(function(e){var t=e.bp,o=23*t,r=13.625*t,a=.25*t;return{photo:{flex:1},photoLong:{flex:2},photoLarge:{height:o},photoLargeSingle:{height:o+r+a},photoLargeTabletPortrait:{height:45*t},photoLargeTabletLandscape:{height:35.25*t},spaceRight:{paddingRight:a},spaceBottom:{marginBottom:a}}})},1665);