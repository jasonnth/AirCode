__d(function(e,r,t,n){"use strict";var o=r(49),s=r(60),i=r(113),a=r(46),l=r(122),p=s.PropTypes,d=["Horizontal","Normal","Small","Large","Inverse","SmallInverse","LargeInverse"],m=function(e,r,t){return p.bool(e,r,t)||function(){var t=e[r],n=e.styleAttr;if(!t&&"Horizontal"!==n)return new Error("indeterminate=false is only valid for styleAttr=Horizontal")}()},u=s.createClass({displayName:"ProgressBarAndroid",propTypes:babelHelpers.extends({},i.propTypes,{styleAttr:p.oneOf(d),indeterminate:m,progress:p.number,color:a,testID:p.string}),getDefaultProps:function(){return{styleAttr:"Normal",indeterminate:!0}},mixins:[o],componentDidMount:function(){this.props.indeterminate&&this.props.styleAttr},render:function(){return s.createElement(c,this.props)}}),c=l("AndroidProgressBar",u,{nativeOnly:{animating:!0}});t.exports=u},299);