declare global {

    interface AppLogQueryDto extends Page {
        id?: number,
        module?: string,
        behavior?: string,
        ipAddress?: string,
        errorMsg?: string,
        logType?: string,
        startTime?: Date,
        endTime?: Date
    }

}

export {AppLogQueryDto}