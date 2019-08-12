__d(function(e,r,p,o){Object.defineProperty(o,"__esModule",{value:!0});var t=r(271),n=babelHelpers.interopRequireDefault(t),s=r(1130),a=r(932),y=r(1297),T=babelHelpers.interopRequireDefault(y),i={width:450,height:300,padding:50},l=[{x:1,y:1,errorX:.1,errorY:.1},{x:2,y:2,errorX:.2,errorY:.2},{x:3,y:3,errorX:.3,errorY:.3},{x:4,y:4,errorX:.4,errorY:.4}],P=function(e){function r(){return babelHelpers.classCallCheck(this,r),babelHelpers.possibleConstructorReturn(this,e.apply(this,arguments))}return babelHelpers.inherits(r,e),r.prototype.renderContainer=function(e,r){var p=e.containerComponent,o=this.getComponentProps(p,"parent","parent");return n.default.cloneElement(p,o,r)},r.prototype.renderGroup=function(e,r){return n.default.cloneElement(this.props.groupComponent,{role:"presentation",style:r},e)},r.prototype.renderData=function(e){for(var r=e.dataComponent,p=e.labelComponent,o=e.groupComponent,t=[],s=[],a=0,y=this.dataKeys.length;a<y;a++){var T=this.getComponentProps(r,"data",a);t[a]=n.default.cloneElement(r,T);var i=this.getComponentProps(p,"labels",a);i&&void 0!==i.text&&null!==i.text&&(s[a]=n.default.cloneElement(p,i))}return s.length>0?n.default.cloneElement.apply(n.default,[o,{}].concat(t,s)):t},r.prototype.shouldAnimate=function(){return!!this.props.animate},r.prototype.render=function(){var e=s.Helpers.modifyProps(this.props,i,"errorbar"),r=e.animate,p=e.style,o=e.standalone,t=e.theme;if(this.shouldAnimate()){var a=["data","domain","height","padding","samples","style","width","x","y","errorX","errorY","borderWidth"];return babelHelpers.jsx(s.VictoryTransition,{animate:r,animationWhitelist:a},void 0,n.default.createElement(this.constructor,e))}var y=t&&t.errorbar&&t.errorbar.style?t.errorbar.style:{},T=s.Helpers.getStyles(p,y,"auto","100%"),l=this.renderGroup(this.renderData(e),T.parent);return o?this.renderContainer(e,l):l},r}(n.default.Component);P.displayName="VictoryErrorBar",P.role="errorBar",P.defaultTransitions=s.DefaultTransitions.discreteTransitions(),P.propTypes={animate:t.PropTypes.object,borderWidth:t.PropTypes.number,categories:t.PropTypes.oneOfType([t.PropTypes.arrayOf(t.PropTypes.string),t.PropTypes.shape({x:t.PropTypes.arrayOf(t.PropTypes.string),y:t.PropTypes.arrayOf(t.PropTypes.string)})]),containerComponent:t.PropTypes.element,data:t.PropTypes.array,domainPadding:t.PropTypes.oneOfType([t.PropTypes.shape({x:t.PropTypes.oneOfType([t.PropTypes.number,s.PropTypes.domain]),y:t.PropTypes.oneOfType([t.PropTypes.number,s.PropTypes.domain])}),t.PropTypes.number]),dataComponent:t.PropTypes.element,domain:t.PropTypes.oneOfType([s.PropTypes.domain,t.PropTypes.shape({x:s.PropTypes.domain,y:s.PropTypes.domain})]),errorX:t.PropTypes.oneOfType([t.PropTypes.func,s.PropTypes.allOfType([s.PropTypes.integer,s.PropTypes.nonNegative]),t.PropTypes.string,t.PropTypes.arrayOf(t.PropTypes.string)]),errorY:t.PropTypes.oneOfType([t.PropTypes.func,s.PropTypes.allOfType([s.PropTypes.integer,s.PropTypes.nonNegative]),t.PropTypes.string,t.PropTypes.arrayOf(t.PropTypes.string)]),events:t.PropTypes.arrayOf(t.PropTypes.shape({target:t.PropTypes.oneOf(["data","labels","parent"]),eventKey:t.PropTypes.oneOfType([t.PropTypes.array,s.PropTypes.allOfType([s.PropTypes.integer,s.PropTypes.nonNegative]),t.PropTypes.string]),eventHandlers:t.PropTypes.object})),eventKey:t.PropTypes.oneOfType([t.PropTypes.func,s.PropTypes.allOfType([s.PropTypes.integer,s.PropTypes.nonNegative]),t.PropTypes.string]),groupComponent:t.PropTypes.element,height:s.PropTypes.nonNegative,horizontal:t.PropTypes.bool,labels:t.PropTypes.oneOfType([t.PropTypes.func,t.PropTypes.array]),labelComponent:t.PropTypes.element,name:t.PropTypes.string,padding:t.PropTypes.oneOfType([t.PropTypes.number,t.PropTypes.shape({top:t.PropTypes.number,bottom:t.PropTypes.number,left:t.PropTypes.number,right:t.PropTypes.number})]),samples:s.PropTypes.nonNegative,scale:t.PropTypes.oneOfType([s.PropTypes.scale,t.PropTypes.shape({x:s.PropTypes.scale,y:s.PropTypes.scale})]),sharedEvents:t.PropTypes.shape({events:t.PropTypes.array,getEventState:t.PropTypes.func}),standalone:t.PropTypes.bool,style:t.PropTypes.shape({parent:t.PropTypes.object,data:t.PropTypes.object,labels:t.PropTypes.object}),theme:t.PropTypes.object,width:s.PropTypes.nonNegative,x:t.PropTypes.oneOfType([t.PropTypes.func,s.PropTypes.allOfType([s.PropTypes.integer,s.PropTypes.nonNegative]),t.PropTypes.string,t.PropTypes.arrayOf(t.PropTypes.string)]),y:t.PropTypes.oneOfType([t.PropTypes.func,s.PropTypes.allOfType([s.PropTypes.integer,s.PropTypes.nonNegative]),t.PropTypes.string,t.PropTypes.arrayOf(t.PropTypes.string),t.PropTypes.arrayOf(t.PropTypes.func)])},P.defaultProps={data:l,scale:"linear",standalone:!0,dataComponent:babelHelpers.jsx(s.ErrorBar,{}),labelComponent:babelHelpers.jsx(s.VictoryLabel,{}),containerComponent:babelHelpers.jsx(s.VictoryContainer,{}),groupComponent:babelHelpers.jsx("g",{}),theme:s.VictoryTheme.grayscale},P.getDomain=T.default.getDomain.bind(T.default),P.getData=s.Data.getData.bind(s.Data),P.getBaseProps=(0,a.partialRight)(T.default.getBaseProps.bind(T.default),i),P.expectedComponents=["dataComponent","labelComponent","groupComponent","containerComponent"],o.default=(0,s.addEvents)(P)},1296);