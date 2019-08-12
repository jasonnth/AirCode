package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.google.common.collect.Sets;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import java.util.Set;
import org.spongycastle.crypto.tls.CipherSuite;

public enum Amenity implements Parcelable {
    TV(1, C0716R.string.amenity_tv),
    Cable(2, C0716R.string.amenity_cable),
    Internet(3, C0716R.string.amenity_internet),
    WirelessInternet(4, C0716R.string.amenity_wireless_internet),
    AC(5, C0716R.string.amenity_AC),
    HandicapAccessible(6, C0716R.string.amenity_handicap),
    Pool(7, C0716R.string.amenity_pool),
    Kitchen(8, C0716R.string.amenity_kitchen),
    ParkingSpace(9, C0716R.string.amenity_parking_space),
    AllowsSmoking(11, C0716R.string.amenity_allows_smoking),
    AllowsPets(12, C0716R.string.amenity_allows_pets),
    Doorman(14, C0716R.string.amenity_doorman),
    Gym(15, C0716R.string.amenity_gym),
    Breakfast(16, C0716R.string.amenity_breakfast),
    HasPets(17, C0716R.string.amenity_has_pets),
    HasPetDogs(18, C0716R.string.amenity_pet_dogs),
    HasPetCats(19, C0716R.string.amenity_pet_cats),
    HasPetOther(20, C0716R.string.amenity_pet_other),
    Elevator(21, C0716R.string.amenity_elevator),
    Jacuzzi(25, C0716R.string.amenity_jacuzzi),
    Fireplace(27, C0716R.string.amenity_fireplace),
    Buzzer(28, C0716R.string.amenity_buzzer),
    Heating(30, C0716R.string.amenity_heating),
    FamilyFriendly(31, C0716R.string.amenity_family_friendly),
    EventFriendly(32, C0716R.string.amenity_event_friendly),
    Washer(33, C0716R.string.amenity_washer),
    Dryer(34, C0716R.string.amenity_dryer),
    SmokeDetector(35, C0716R.string.amenity_smoke_detector),
    CarbonMonoxideDetector(36, C0716R.string.amenity_carbon_monoxide_detector),
    FirstAidKit(37, C0716R.string.amenity_first_aid),
    SafetyCard(38, C0716R.string.amenity_safety_card),
    FireExtinguisher(39, C0716R.string.amenity_fire_extinguisher),
    Essentials(40, C0716R.string.amenity_essentials),
    Shampoo(41, C0716R.string.amenity_shampoo),
    BedroomDoorLock(42, C0716R.string.amenity_bedroom_door_lock),
    TwentyFourHourCheckIn(43, C0716R.string.amenity_twentyfourhourcheckin),
    Hangers(44, C0716R.string.amenity_hangers),
    HairDryer(45, C0716R.string.amenity_hair_dryer),
    Iron(46, C0716R.string.amenity_iron),
    LaptopFriendly(47, C0716R.string.amenity_desk_workspace),
    SelfCheckIn(51, C0716R.string.amenity_self_checkin),
    SmartLock(52, C0716R.string.amenity_smart_lock),
    Keypad(53, C0716R.string.amenity_keypad),
    Lockbox(54, C0716R.string.amenity_lockbox),
    PrivateLivingRoom(56, C0716R.string.amenity_private_living_room),
    PrivateEntrance(57, C0716R.string.amenity_private_entrance),
    TVOrCable(58, C0716R.string.amenity_tv),
    BabyMonitor(59, C0716R.string.amenity_baby_monitor),
    OutletCovers(60, C0716R.string.amenity_outlet_covers),
    Bathtub(61, C0716R.string.amenity_bathtub),
    BabyBath(62, C0716R.string.amenity_baby_bath),
    ChangingTable(63, C0716R.string.amenity_changing_table),
    HighChair(64, C0716R.string.amenity_high_chair),
    StairGates(65, C0716R.string.amenity_stair_gates),
    ChildrensBooksAndToys(66, C0716R.string.amenity_books_and_toys),
    WindowGuards(67, C0716R.string.amenity_window_guards),
    TableCornerGuards(68, C0716R.string.amenity_corner_guards),
    FireplaceGuards(69, C0716R.string.amenity_fireplace_guards),
    BabysitterRecommendations(70, C0716R.string.amenity_recommended_babysitter),
    Crib(71, C0716R.string.amenity_crib),
    PackNPlayTravelCrib(72, C0716R.string.amenity_pack_n_play),
    RoomDarkeningShades(73, C0716R.string.amenity_room_darkening_shades),
    ChildrensDinnerware(74, C0716R.string.amenity_childrens_dinnerware),
    GameConsole(75, C0716R.string.amenity_game_console),
    WaterFront(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, C0716R.string.amenity_waterfront),
    LakeAccess(133, C0716R.string.amenity_lake_access),
    Beachfront(134, C0716R.string.amenity_beachfront),
    SkiInSkiOut(135, C0716R.string.amenity_ski_in_ski_out);
    
    public static final Creator<Amenity> CREATOR = null;
    private static final Set<Amenity> FAMILY_AMENITIES = null;
    private static final Lazy<SparseArray<Amenity>> lookupArray = null;

    /* renamed from: id */
    public final int f8471id;
    public final int stringRes;

    static {
        FAMILY_AMENITIES = Sets.newHashSet(BabyMonitor, BabyBath, BabysitterRecommendations, Bathtub, ChangingTable, ChildrensBooksAndToys, ChildrensDinnerware, Crib, FireplaceGuards, GameConsole, HighChair, OutletCovers, PackNPlayTravelCrib, RoomDarkeningShades, StairGates, TableCornerGuards, WindowGuards);
        lookupArray = DoubleCheck.lazy(Amenity$$Lambda$1.lambdaFactory$());
        CREATOR = new Creator<Amenity>() {
            public Amenity createFromParcel(Parcel source) {
                return Amenity.values()[source.readInt()];
            }

            public Amenity[] newArray(int size) {
                return new Amenity[size];
            }
        };
    }

    private Amenity(int id, int stringRes2) {
        this.f8471id = id;
        this.stringRes = stringRes2;
    }

    /* renamed from: id */
    public int mo58911id() {
        return this.f8471id;
    }

    public static Amenity forId(int id) {
        Amenity result = (Amenity) ((SparseArray) lookupArray.get()).get(id);
        if (result == null) {
            switch (id) {
                case 10:
                case 13:
                case 22:
                case 23:
                case 24:
                case 26:
                case 29:
                case 49:
                case 50:
                    break;
                default:
                    if (Math.random() < 0.01d) {
                        BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Unknown amenity id: " + id));
                        break;
                    }
                    break;
            }
        }
        return result;
    }

    public boolean hasDrawableRes() {
        return getDrawableRes() != 0;
    }

    public int getDrawableRes() {
        if (!isFamilyAmenity()) {
            switch (this) {
                case TV:
                    return C0716R.C0717drawable.n2_ic_am_tv;
                case Cable:
                case TVOrCable:
                    return C0716R.C0717drawable.n2_ic_am_cabletv;
                case Internet:
                    return C0716R.C0717drawable.n2_ic_am_internet;
                case WirelessInternet:
                    return C0716R.C0717drawable.n2_ic_am_wifi;
                case AC:
                    return C0716R.C0717drawable.n2_ic_am_ac;
                case HandicapAccessible:
                    return C0716R.C0717drawable.n2_ic_am_handicap;
                case Pool:
                    return C0716R.C0717drawable.n2_ic_am_pool;
                case Kitchen:
                    return C0716R.C0717drawable.n2_ic_am_kitchen;
                case ParkingSpace:
                    return C0716R.C0717drawable.n2_ic_am_parking;
                case AllowsSmoking:
                    return C0716R.C0717drawable.n2_ic_am_smokingok;
                case AllowsPets:
                    return C0716R.C0717drawable.n2_ic_am_pets;
                case Doorman:
                    return C0716R.C0717drawable.n2_ic_am_doorman;
                case Gym:
                    return C0716R.C0717drawable.n2_ic_am_gym;
                case Breakfast:
                    return C0716R.C0717drawable.n2_ic_am_breakfast;
                case HasPets:
                    return C0716R.C0717drawable.n2_ic_am_pets;
                case HasPetDogs:
                    return C0716R.C0717drawable.n2_ic_am_dog;
                case HasPetCats:
                    return C0716R.C0717drawable.n2_ic_am_cat;
                case HasPetOther:
                    return C0716R.C0717drawable.n2_ic_am_pets;
                case Elevator:
                    return C0716R.C0717drawable.n2_ic_am_elevator;
                case Jacuzzi:
                    return C0716R.C0717drawable.n2_ic_am_hottub;
                case Fireplace:
                    return C0716R.C0717drawable.n2_ic_am_fireplace;
                case Buzzer:
                    return C0716R.C0717drawable.n2_ic_am_buzzer;
                case Heating:
                    return C0716R.C0717drawable.n2_ic_am_heating;
                case FamilyFriendly:
                    return C0716R.C0717drawable.n2_ic_am_familyfriendly;
                case EventFriendly:
                    return C0716R.C0717drawable.n2_ic_am_events;
                case Washer:
                    return C0716R.C0717drawable.n2_ic_am_washer;
                case Dryer:
                    return C0716R.C0717drawable.n2_ic_am_dryer;
                case SmokeDetector:
                    return C0716R.C0717drawable.n2_ic_am_smoke_detector;
                case CarbonMonoxideDetector:
                    return C0716R.C0717drawable.n2_ic_am_co_detector;
                case FirstAidKit:
                    return C0716R.C0717drawable.n2_ic_am_first_aid;
                case SafetyCard:
                    return C0716R.C0717drawable.n2_ic_am_safety_card;
                case FireExtinguisher:
                    return C0716R.C0717drawable.n2_ic_am_fire_extinguisher;
                case Essentials:
                    return C0716R.C0717drawable.n2_ic_am_essentials;
                case Shampoo:
                    return C0716R.C0717drawable.n2_ic_am_shampoo;
                case TwentyFourHourCheckIn:
                    return C0716R.C0717drawable.n2_ic_am_twentyfourhourcheckin;
                case Hangers:
                    return C0716R.C0717drawable.n2_ic_am_hangers;
                case HairDryer:
                    return C0716R.C0717drawable.n2_ic_am_hair_dryer;
                case Iron:
                    return C0716R.C0717drawable.n2_ic_am_iron;
                case LaptopFriendly:
                    return C0716R.C0717drawable.n2_ic_am_laptop_friendly;
                case BedroomDoorLock:
                    return C0716R.C0717drawable.n2_ic_lock;
                case PrivateEntrance:
                    return C0716R.C0717drawable.n2_ic_am_private_entrance;
                case PrivateLivingRoom:
                    return C0716R.C0717drawable.n2_ic_am_private_living_room;
                case SelfCheckIn:
                case WaterFront:
                case LakeAccess:
                case Beachfront:
                case SkiInSkiOut:
                    return 0;
                case SmartLock:
                case Keypad:
                case Lockbox:
                    return C0716R.C0717drawable.n2_ic_keypad_lock;
                default:
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Undrawable P3 amenity: " + this));
                    return 0;
            }
        } else if (!Trebuchet.launch(TrebuchetKeys.P3_FAMILY_FRIENDLY_AMENITIES)) {
            return 0;
        } else {
            switch (this) {
                case BabyMonitor:
                    return C0716R.C0717drawable.n2_ic_am_baby_monitor;
                case OutletCovers:
                    return C0716R.C0717drawable.n2_ic_am_outlet_cover;
                case Bathtub:
                    return C0716R.C0717drawable.n2_ic_am_bathtub;
                case BabyBath:
                    return C0716R.C0717drawable.n2_ic_am_baby_bathtub;
                case ChangingTable:
                    return C0716R.C0717drawable.n2_ic_am_changing_table;
                case HighChair:
                    return C0716R.C0717drawable.n2_ic_am_highchair;
                case StairGates:
                    return C0716R.C0717drawable.n2_ic_am_stair_gates;
                case ChildrensBooksAndToys:
                    return C0716R.C0717drawable.n2_ic_am_books_and_toys;
                case WindowGuards:
                    return C0716R.C0717drawable.n2_ic_am_window_locks;
                case TableCornerGuards:
                    return C0716R.C0717drawable.n2_ic_am_corner_guard;
                case FireplaceGuards:
                    return C0716R.C0717drawable.n2_ic_am_fireplace_guards;
                case BabysitterRecommendations:
                    return C0716R.C0717drawable.n2_ic_am_nanny_babysitter;
                case Crib:
                    return C0716R.C0717drawable.n2_ic_am_crib;
                case PackNPlayTravelCrib:
                    return C0716R.C0717drawable.n2_ic_am_packnplay;
                case RoomDarkeningShades:
                    return C0716R.C0717drawable.n2_ic_am_darkening_shades;
                case ChildrensDinnerware:
                    return C0716R.C0717drawable.n2_ic_am_childrens_dinnerware;
                case GameConsole:
                    return C0716R.C0717drawable.n2_ic_am_game_console;
                default:
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Undrawable P3 family amenity: " + this));
                    return 0;
            }
        }
    }

    public boolean isFamilyAmenity() {
        return FAMILY_AMENITIES.contains(this);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
