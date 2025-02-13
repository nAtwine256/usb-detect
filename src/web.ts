import { WebPlugin } from '@capacitor/core';

import type { UsbDetectorPlugin } from './definitions';

export class UsbDetectorWeb extends WebPlugin implements UsbDetectorPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
