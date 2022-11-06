clc
clear
close

[file, path] = uigetfile('*.wav');
% [file, path] = uigetfile("/Users/qayyuma/Downloads/Rips/Grover Washington Jr/Winelight/06 Make Me A Memory copy.wav");

% directory = ('/Users/qayyuma/Downloads/Encrypted Songs/');
% files = dir(fullfile(directory, '*.wav'));
% path = directory;


% for i = 1:length(files)
% file = fullfile(files(i).name);

filename = append(path,file);
[y,Fs] = audioread(filename);

music = y(:,1);
numIntervals = 100;

musicSplit = musicSplitter(music, numIntervals);
[intervalKey, musicEncrypted] = musicEncrypter(musicSplit, numIntervals);

fileGeneric = erase(file,".wav");
fileWrite = append(path, fileGeneric,".smores");

save(fileWrite,"intervalKey","musicEncrypted","numIntervals","Fs");
disp(append("Finished saving ", fileGeneric));


% if i ~= 0  
%     intervalKey = 0;
%     music = 0;
%     musicEncrypted = 0;
%     musicSplit = 0;
%     numIntervals = 0;
% end

% end

% sound(y(1:2205000),Fs);


function musicSplit = musicSplitter(music, numIntervals)

samples = length(music);
numSamples = floor(samples / numIntervals);
musicSplit = zeros(numIntervals,numSamples);

count = 1;

for i = 1:numIntervals
    for j = 1:numSamples
        musicSplit(i,j) = music(count);
        count = count + 1;
    end
end
end

function [intervalKey, musicEncrypted] = musicEncrypter(musicSplit, numIntervals)

[a,b] = size(musicSplit);
intervals = ones([1 numIntervals]);
samples = b;
intervalKey = ones([1 numIntervals]);
musicEncrypted = zeros(a,b);

for i = 1:numIntervals
    intervals(i) = i;
    intervalKey(i) = -1;
    valid = false;
    while valid == false
        selectedInterval = randi(numIntervals);
        match = arrayComparer(intervalKey, selectedInterval);
        if (match == false)
            intervalKey(i) = selectedInterval;
            for j = 1:samples
                musicEncrypted(i,j) = musicSplit(intervalKey(i),j);
            end
            break;
        end
    end
end

end

function [win] = arrayComparer(input, flag) 
win = false;
for i = 1:length(input)
    if flag == input(i)
        win = true;
        break;
    end
end
end