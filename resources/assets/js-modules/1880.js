__d(function(e,t,i,n){"use strict";function r(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var i in e)Object.prototype.hasOwnProperty.call(e,i)&&(t[i]=e[i]);return t.default=e,t}Object.defineProperty(n,"__esModule",{value:!0}),n.GiftingFlowClickAddGiftEvent=void 0;var d=t(412),o=function(e){return e&&e.__esModule?e:{default:e}}(d),a=t(694),u=r(a),f=t(698),l=r(f);n.GiftingFlowClickAddGiftEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.GiftingFlow:GiftingFlowClickAddGiftEvent:3.0.0",event_name:"giftingflow_click_add_gift",page:"gifting_redemption",section:"add_to_account",target:"add_to_account_button",operation:2},propTypes:{schema:o.default.string,event_name:o.default.string.isRequired,context:o.default.shape(u.Context.propTypes).isRequired,page:o.default.string.isRequired,section:o.default.string.isRequired,target:o.default.string.isRequired,operation:o.default.oneOf(Object.values(l.Operation)).isRequired,gift_card_code:o.default.string.isRequired}}},1880);