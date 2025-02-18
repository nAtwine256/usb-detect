import { WebPlugin } from '@capacitor/core';
import type { UsbDetectorPlugin } from './definitions';

export class UsbDetectorWeb extends WebPlugin implements UsbDetectorPlugin {
  echo(_: { value: string; }): Promise<{ value: string; }> {
    throw new Error('Method not implemented.');
  }

}

