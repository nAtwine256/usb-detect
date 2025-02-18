import { Plugin } from "@capacitor/core";

export enum UsbEvent {
  ATTACHED = "usbAttached",
  DETACHED = "usbDetached"
}

export type UsbDevice = {
  deviceName: string;
  deviceId: number;
}

export type ListenerCallback = (device: UsbDevice) => void;
                                

export interface UsbDetectorPlugin extends Plugin{
  echo (options: {value: string}) : Promise<{value:string}>;
}
