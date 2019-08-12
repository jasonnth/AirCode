package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzm;
import java.util.LinkedList;
import java.util.List;

@zzme
class zzix {
    /* access modifiers changed from: private */
    public final List<zza> zzth = new LinkedList();

    interface zza {
        void zzb(zziy zziy) throws RemoteException;
    }

    zzix() {
    }

    /* access modifiers changed from: 0000 */
    public void zza(final zziy zziy) {
        Handler handler = zzpo.zzXC;
        for (final zza zza2 : this.zzth) {
            handler.post(new Runnable(this) {
                public void run() {
                    try {
                        zza2.zzb(zziy);
                    } catch (RemoteException e) {
                        zzpk.zzc("Could not propagate interstitial ad event.", e);
                    }
                }
            });
        }
        this.zzth.clear();
    }

    /* access modifiers changed from: 0000 */
    public void zzc(zzm zzm) {
        zzm.zza((zzep) new com.google.android.gms.internal.zzep.zza() {
            public void onAdClosed() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zztk != null) {
                            zziy.zztk.onAdClosed();
                        }
                    }
                });
            }

            public void onAdFailedToLoad(final int i) throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zztk != null) {
                            zziy.zztk.onAdFailedToLoad(i);
                        }
                    }
                });
                zzpk.m1279v("Pooled interstitial failed to load.");
            }

            public void onAdLeftApplication() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zztk != null) {
                            zziy.zztk.onAdLeftApplication();
                        }
                    }
                });
            }

            public void onAdLoaded() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zztk != null) {
                            zziy.zztk.onAdLoaded();
                        }
                    }
                });
                zzpk.m1279v("Pooled interstitial loaded.");
            }

            public void onAdOpened() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zztk != null) {
                            zziy.zztk.onAdOpened();
                        }
                    }
                });
            }
        });
        zzm.zza((zzev) new com.google.android.gms.internal.zzev.zza() {
            public void onAppEvent(final String str, final String str2) throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIV != null) {
                            zziy.zzIV.onAppEvent(str, str2);
                        }
                    }
                });
            }
        });
        zzm.zza((zzle) new com.google.android.gms.internal.zzle.zza() {
            public void zza(final zzld zzld) throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIW != null) {
                            zziy.zzIW.zza(zzld);
                        }
                    }
                });
            }
        });
        zzm.zza((zzgp) new com.google.android.gms.internal.zzgp.zza() {
            public void zza(final zzgo zzgo) throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIX != null) {
                            zziy.zzIX.zza(zzgo);
                        }
                    }
                });
            }
        });
        zzm.zza((zzeo) new com.google.android.gms.internal.zzeo.zza() {
            public void onAdClicked() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIY != null) {
                            zziy.zzIY.onAdClicked();
                        }
                    }
                });
            }
        });
        zzm.zza((zznw) new com.google.android.gms.internal.zznw.zza() {
            public void onRewardedVideoAdClosed() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIZ != null) {
                            zziy.zzIZ.onRewardedVideoAdClosed();
                        }
                    }
                });
            }

            public void onRewardedVideoAdFailedToLoad(final int i) throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIZ != null) {
                            zziy.zzIZ.onRewardedVideoAdFailedToLoad(i);
                        }
                    }
                });
            }

            public void onRewardedVideoAdLeftApplication() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIZ != null) {
                            zziy.zzIZ.onRewardedVideoAdLeftApplication();
                        }
                    }
                });
            }

            public void onRewardedVideoAdLoaded() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIZ != null) {
                            zziy.zzIZ.onRewardedVideoAdLoaded();
                        }
                    }
                });
            }

            public void onRewardedVideoAdOpened() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIZ != null) {
                            zziy.zzIZ.onRewardedVideoAdOpened();
                        }
                    }
                });
            }

            public void onRewardedVideoStarted() throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIZ != null) {
                            zziy.zzIZ.onRewardedVideoStarted();
                        }
                    }
                });
            }

            public void zza(final zznt zznt) throws RemoteException {
                zzix.this.zzth.add(new zza(this) {
                    public void zzb(zziy zziy) throws RemoteException {
                        if (zziy.zzIZ != null) {
                            zziy.zzIZ.zza(zznt);
                        }
                    }
                });
            }
        });
    }
}
