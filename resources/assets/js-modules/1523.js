__d(function(e,t,i,r){Object.defineProperty(r,"__esModule",{value:!0});var l=t(759),a=babelHelpers.interopRequireDefault(l),o=t(765),n=babelHelpers.interopRequireDefault(o),u=t(1524),s=babelHelpers.interopRequireDefault(u),f=t(1527),p=babelHelpers.interopRequireDefault(f),c=t(412),d=babelHelpers.interopRequireDefault(c),h=t(271),b=(babelHelpers.interopRequireDefault(h),t(790)),_=t(44),T=t(379),I=babelHelpers.interopRequireDefault(T),y=t(410),R=babelHelpers.interopRequireDefault(y),A=t(1043),E=babelHelpers.interopRequireDefault(A),F=t(1486),N=babelHelpers.interopRequireDefault(F),O=t(1473),L=babelHelpers.interopRequireDefault(O),P=t(1470),C=t(1466),v=t(1529),m=t(1478),g=function(e){function t(i){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,i));return r.initializeFailureTypeAndCopies(),r.onPressPrimaryButton=r.onPressPrimaryButton.bind(r),r}return babelHelpers.inherits(t,e),t.prototype.getAvailableAOVFrictions=function(){var e=(0,n.default)(this.props.frictions);return(0,s.default)(e,C.FRICTION_GROUP_ACCOUNT_OWNERSHIP_VERIFICATION)},t.prototype.getFailureType=function(){var e=this.props.failedFrictions;return 0===(0,p.default)(this.getAvailableAOVFrictions(),e).length?v.FAILURE_TYPE_ALL_FRICTIONS:e.indexOf(C.FRICTION_TYPE_CONTACT_KBA)<0?v.FAILURE_TYPE_SINGLE_NON_KBA_FRICTION:v.FAILURE_TYPE_KBA},t.prototype.shouldComponentUpdate=function(){return!1},t.prototype.initializeFailureTypeAndCopies=function(){this.failureType=this.props.failureType||this.getFailureType(),this.title=this.failureType===v.FAILURE_TYPE_SINGLE_NON_KBA_FRICTION?I.default.phrase("Having trouble?",null,"title for the screen informing user what to do after failed one account verification method"):I.default.phrase("Need our help?",null,"title for the screen informing user that a ticket is created after user failed one account verificaiton method"),this.subtitle=this.failureType===v.FAILURE_TYPE_SINGLE_NON_KBA_FRICTION?I.default.phrase("We couldn\u2019t confirm your account ownership based on the info you provided.",null,"subtitle for the screen informing user what to do after failed one account verification method"):I.default.phrase("Looks like you\u2019re having trouble completing your account ownership verification.\n\nWe\u2019ve created a support case for you, and someone from our team will follow up by email within 48 hrs.",null,"subtitle for the screen informing user that a ticket is created after user failed one account verificaiton method"),this.buttonText=this.failureType===v.FAILURE_TYPE_ALL_FRICTIONS?I.default.phrase("OK",null,"a button to exit after user fails all the account verification methods"):I.default.phrase("Try another verification",null,"a button to try another account verification method after previous one failed"),this.shouldShowTicketCreationExplanation=this.failureType===v.FAILURE_TYPE_KBA||this.failureType===v.FAILURE_TYPE_ALL_FRICTIONS},t.prototype.onPressPrimaryButton=function(){(0,m.logPressContinue)(this.props.airlockId,m.PAGE_NAMES.AOV_FRICTION_FAILURE_SCREEN,{failureType:this.failureType}),R.default.pop(null,{navigationTag:"FrictionManager"})},t.prototype.render=function(){return babelHelpers.jsx(_.View,{style:_.StyleSheet.absoluteFill},void 0,babelHelpers.jsx(L.default,{leadingButtonVisible:!1,leftIcon:R.default.LEFT_ICON.NONE}),babelHelpers.jsx(N.default,{light:!0,title:this.title,subtitle:this.subtitle},void 0,this.failureType!==v.FAILURE_TYPE_ALL_FRICTIONS&&babelHelpers.jsx(E.default,{dark:!0,onPress:this.onPressPrimaryButton},void 0,this.buttonText)))},t}(h.PureComponent);g.propTypes={airlockId:d.default.number,frictions:d.default.arrayOf(d.default.arrayOf(d.default.string)),failedFrictions:d.default.arrayOf(d.default.string),failureType:d.default.oneOf([v.FAILURE_TYPE_SINGLE_NON_KBA_FRICTION,v.FAILURE_TYPE_ALL_FRICTIONS,v.FAILURE_TYPE_KBA])},g.defaultProps={airlockId:null,frictions:[],failedFrictions:[],failureType:null},r.default=(0,b.connect)(function(e){return babelHelpers.extends({},(0,a.default)((0,P.frictionManagerSelector)(e),["airlockId","frictions"]),(0,a.default)((0,P.AOVSelector)(e),["failedFrictions"]))})(g)},1523);