__d(function(e,t,n,o){Object.defineProperty(o,"__esModule",{value:!0});var l=t(1257),r=(babelHelpers.interopRequireDefault(l),t(44)),s=t(928),i=t(1071),a=t(1279),p=function(e){function t(){return babelHelpers.classCallCheck(this,t),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(t,e),t.prototype.shouldAnimate=function(){return"android"!==r.Platform.OS&&Boolean(this.props.animate)},t}(a.VictoryAxis);p.defaultProps=babelHelpers.extends({},a.VictoryAxis.defaultProps,{axisComponent:babelHelpers.jsx(i.Line,{}),axisLabelComponent:babelHelpers.jsx(i.VictoryLabel,{}),tickLabelComponent:babelHelpers.jsx(i.VictoryLabel,{}),tickComponent:babelHelpers.jsx(i.Line,{}),gridComponent:babelHelpers.jsx(i.Line,{}),containerComponent:babelHelpers.jsx(i.VictoryContainer,{}),groupComponent:babelHelpers.jsx(s.G,{}),width:r.Dimensions.get("window").width}),o.default=p},1307);