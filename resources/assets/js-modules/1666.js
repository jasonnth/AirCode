__d(function(e,t,o,l){Object.defineProperty(l,"__esModule",{value:!0});var r=t(834),a=t(271),s=babelHelpers.interopRequireDefault(a),n=t(44),i=t(422),p=babelHelpers.interopRequireDefault(i),b=t(1663),h={children:r.Types.element.isRequired,first:r.Types.bool.isRequired,layout:r.Types.oneOf([b.LAYOUT_HORIZONTAL,b.LAYOUT_VERTICAL]),numberOfPhotosInGrid:r.Types.number.isRequired,onPress:r.Types.func,tablet:r.Types.bool},u={layout:b.LAYOUT_VERTICAL,onPress:function(){},tablet:!1},c=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.render=function(){var e=this.props,t=e.children,o=e.first,l=e.layout,r=e.numberOfPhotosInGrid,a=e.onPress,i=e.tablet,p=l===b.LAYOUT_HORIZONTAL,h=void 0;return h=i?p?r>2?d.photoTabletLandscape:d.photoTabletLandscapeTall:d.photoTabletPortrait:d.photoSmall,babelHelpers.jsx(n.View,{style:[d.photo,h,o&&r>2&&(p?d.spaceBottom:d.spaceRight)]},void 0,babelHelpers.jsx(n.TouchableHighlight,{onPress:a},void 0,s.default.cloneElement(t,{style:[h,t.props.style]})),!o&&r-b.NUMBER_OF_PHOTOS_TO_SHOW>0&&babelHelpers.jsx(n.TouchableHighlight,{onPress:a,style:n.StyleSheet.absoluteFill},void 0,babelHelpers.jsx(n.View,{style:[d.photoCountContainer,d.transparentBackground,n.StyleSheet.absoluteFill]},void 0,babelHelpers.jsx(n.Text,{style:d.photoCountText},void 0,"+"+(r-b.NUMBER_OF_PHOTOS_TO_SHOW)))))},t}(s.default.Component);l.default=c,c.defaultProps=u,c.propTypes=h;var d=p.default.create(function(e){var t=e.bp,o=e.font,l=13.625*t,r=.25*t;return{photoCountContainer:{alignItems:"center",flex:1,flexDirection:"row",justifyContent:"center"},photoCountText:babelHelpers.extends({},o.title3InversePlus,{color:"#ffffff"}),photo:{flex:1},photoLargeTabletLandscape:{height:53*t},photoSmall:{height:l},photoTabletLandscape:{height:17.5*t},photoTabletLandscapeTall:{height:35.25*t},photoTabletPortrait:{height:26.625*t},spaceRight:{paddingRight:r},spaceBottom:{marginBottom:r},transparentBackground:{backgroundColor:"rgba(0, 0, 0, 0.5)"}}})},1666);