__d(function(o,e,n,t){"use strict";var s=e(252),i=e(49),r=e(60),p=(e(119),e(56)),c=e(113),l=e(46),a=e(122),h=e(163),d=r.PropTypes,v=d.oneOfType([s.propTypes.source,d.oneOf([])]),b=r.createClass({displayName:"ToolbarAndroid",mixins:[i],propTypes:babelHelpers.extends({},c.propTypes,{actions:d.arrayOf(d.shape({title:d.string.isRequired,icon:v,show:d.oneOf(["always","ifRoom","never"]),showWithText:d.bool})),logo:v,navIcon:v,onActionSelected:d.func,onIconClicked:d.func,overflowIcon:v,subtitle:d.string,subtitleColor:l,title:d.string,titleColor:l,contentInsetStart:d.number,contentInsetEnd:d.number,rtl:d.bool,testID:d.string}),render:function(){var o=babelHelpers.extends({},this.props);if(this.props.logo&&(o.logo=h(this.props.logo)),this.props.navIcon&&(o.navIcon=h(this.props.navIcon)),this.props.overflowIcon&&(o.overflowIcon=h(this.props.overflowIcon)),this.props.actions){for(var e=[],n=0;n<this.props.actions.length;n++){var t=babelHelpers.extends({},this.props.actions[n]);t.icon&&(t.icon=h(t.icon)),t.show&&(t.show=p.ToolbarAndroid.Constants.ShowAsAction[t.show]),e.push(t)}o.nativeActions=e}return r.createElement(f,babelHelpers.extends({onSelect:this._onSelect},o))},_onSelect:function(o){var e=o.nativeEvent.position;-1===e?this.props.onIconClicked&&this.props.onIconClicked():this.props.onActionSelected&&this.props.onActionSelected(e)}}),f=a("ToolbarAndroid",b,{nativeOnly:{nativeActions:!0}});n.exports=b},319);